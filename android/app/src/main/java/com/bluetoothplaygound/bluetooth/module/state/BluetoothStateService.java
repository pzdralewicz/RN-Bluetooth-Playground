package com.bluetoothplaygound.bluetooth.module.state;

import com.bluetoothplaygound.bluetooth.EventDispatcher;
import com.facebook.react.bridge.ReactApplicationContext;

class BluetoothStateService {

  private final EventDispatcher eventDispatcher = new EventDispatcher();

  public void turnedOff(ReactApplicationContext reactContext) {
    eventDispatcher.sendEvent(reactContext, STATE.TURNED_OFF.name());
  }

  public void turnedOn(ReactApplicationContext reactContext) {
    eventDispatcher.sendEvent(reactContext, STATE.TURNED_ON.name());
  }

  private enum STATE {
    TURNED_ON,
    TURNED_OFF
  }
}
