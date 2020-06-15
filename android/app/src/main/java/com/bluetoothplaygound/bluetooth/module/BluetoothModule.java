package com.bluetoothplaygound.bluetooth.module;

import androidx.annotation.NonNull;
import com.bluetoothplaygound.bluetooth.module.connection.ConnectionListener;
import com.bluetoothplaygound.bluetooth.module.state.BluetoothStateListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class BluetoothModule extends ReactContextBaseJavaModule {

  public BluetoothModule(ReactApplicationContext reactContext) {
    super(reactContext);
    new ConnectionListener(getReactApplicationContext());
    new BluetoothStateListener(getReactApplicationContext());
  }

  @NonNull
  @Override
  public String getName() {
    return "BluetoothModule";
  }
}
