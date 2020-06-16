package com.bluetoothplayground.bluetooth.module;

import androidx.annotation.NonNull;
import com.bluetoothplayground.bluetooth.module.connection.ConnectionListener;
import com.bluetoothplayground.bluetooth.module.paired.devices.PairedDevices;
import com.bluetoothplayground.bluetooth.module.state.BluetoothStateListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class BluetoothModule extends ReactContextBaseJavaModule {

  public BluetoothModule(ReactApplicationContext reactContext) {
    super(reactContext);
    new ConnectionListener(getReactApplicationContext());
    new BluetoothStateListener(getReactApplicationContext());
    new PairedDevices();
  }

  @NonNull
  @Override
  public String getName() {
    return "BluetoothModule";
  }
}
