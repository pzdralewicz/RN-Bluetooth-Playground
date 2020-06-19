package com.bluetoothplayground.bluetooth.module.state;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import com.bluetoothplayground.bluetooth.EventDispatcher;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;

public class BluetoothStateService {

  private final EventDispatcher eventDispatcher = new EventDispatcher();
  private final ReactApplicationContext reactContext;

  public BluetoothStateService(ReactApplicationContext reactApplicationContext) {
    this.reactContext = reactApplicationContext;
  }

  public void turnedOff() {
    eventDispatcher.sendEvent(reactContext, STATE.TURNED_OFF.name());
  }

  public void turnedOn() {
    eventDispatcher.sendEvent(reactContext, STATE.TURNED_ON.name());
  }

  public void isTurnedOn(Promise promise) {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    boolean turnedOn = false;
    if (mBluetoothAdapter.isEnabled()) {
      turnedOn = true;
    }

    promise.resolve(turnedOn);
  }

  public void enableBluetooth() {
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (!bluetoothAdapter.isEnabled()) {
      Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      reactContext.startActivityForResult(intent, 1, Bundle.EMPTY);
    }
  }

  private enum STATE {
    TURNED_ON,
    TURNED_OFF
  }
}
