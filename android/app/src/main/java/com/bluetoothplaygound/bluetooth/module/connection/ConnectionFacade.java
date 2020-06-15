package com.bluetoothplaygound.bluetooth.module.connection;

import android.bluetooth.BluetoothDevice;
import com.bluetoothplaygound.bluetooth.EventDispatcher;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

class ConnectionFacade {

  private final EventDispatcher eventDispatcher = new EventDispatcher();

  private WritableMap queryWritableMapFromDevice(BluetoothDevice device) {
    WritableMap params = Arguments.createMap();
    params.putString("name", device.getName());
    params.putString("address", device.getAddress());
    return params;
  }

  public void connected(BluetoothDevice device, ReactApplicationContext reactContext) {
    eventDispatcher.sendEvent(
        reactContext, STATUS.CONNECTED.name(), queryWritableMapFromDevice(device));
  }

  public void disconnected(BluetoothDevice device, ReactApplicationContext reactContext) {
    eventDispatcher.sendEvent(
        reactContext, STATUS.DISCONNECTED.name(), queryWritableMapFromDevice(device));
  }

  private enum STATUS {
    CONNECTED,
    DISCONNECTED
  }
}
