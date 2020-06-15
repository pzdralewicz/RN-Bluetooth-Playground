package com.bluetoothplayground.bluetooth.module.connection;

import static com.bluetoothplayground.bluetooth.BluetoothDeviceMapFactory.queryWritableMapFromDevice;

import android.bluetooth.BluetoothDevice;
import com.bluetoothplayground.bluetooth.EventDispatcher;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

class ConnectionService {

  private final EventDispatcher eventDispatcher = new EventDispatcher();

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
