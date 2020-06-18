package com.bluetoothplayground.bluetooth.module;

import androidx.annotation.NonNull;
import com.bluetoothplayground.bluetooth.module.connection.ConnectionListener;
import com.bluetoothplayground.bluetooth.module.connection.ConnectionService;
import com.bluetoothplayground.bluetooth.module.discover.DiscoverDevices;
import com.bluetoothplayground.bluetooth.module.paired.devices.PairedDevices;
import com.bluetoothplayground.bluetooth.module.state.BluetoothStateListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BluetoothModule extends ReactContextBaseJavaModule {

  private final ReactContext reactContext;

  public BluetoothModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    new ConnectionListener(getReactApplicationContext());
    new BluetoothStateListener(getReactApplicationContext());
  }

  @NonNull
  @Override
  public String getName() {
    return "BluetoothModule";
  }

  @ReactMethod
  public void listPaired(Promise promise) {
    new PairedDevices().queryPaired(promise);
  }

  @ReactMethod
  public void listUnpaired(Promise promise) {
    new DiscoverDevices(reactContext).findDevices(promise);
  }

  @ReactMethod
  public void connect(String address, Promise promise) {
    new ConnectionService().connectToAddress(address, promise,reactContext);
  }
}
