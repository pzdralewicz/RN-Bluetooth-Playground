package com.bluetoothplaygound.connection.events;

import android.bluetooth.BluetoothDevice;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

public class ConnectionFacade {

  private final ConnectionDispatcher connectionDispatcher = new ConnectionDispatcher();

  private WritableMap queryWritableMapFromDevice(BluetoothDevice device) {
    WritableMap params = Arguments.createMap();
    params.putString("deviceName", device.getName());
    params.putString("deviceAddress", device.getAddress());
    return params;
  }

  public void connected(BluetoothDevice device, ReactApplicationContext reactContext) {
    connectionDispatcher.sendEvent(
        reactContext, STATUS.CONNECTED.name(), queryWritableMapFromDevice(device));
  }

  public void disconnected(BluetoothDevice device, ReactApplicationContext reactContext) {
    connectionDispatcher.sendEvent(
        reactContext, STATUS.DISCONNECTED.name(), queryWritableMapFromDevice(device));
  }

  private enum STATUS {
    CONNECTED,
    DISCONNECTED
  }
}
