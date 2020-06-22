package com.bluetoothplayground.bluetooth.module;

import android.os.Build.VERSION_CODES;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bluetoothplayground.bluetooth.module.connection.ConnectionListener;
import com.bluetoothplayground.bluetooth.module.connection.ConnectionService;
import com.bluetoothplayground.bluetooth.module.discover.DiscoverDevices;
import com.bluetoothplayground.bluetooth.module.paired.devices.PairedDevices;
import com.bluetoothplayground.bluetooth.module.state.BluetoothStateListener;
import com.bluetoothplayground.bluetooth.module.state.BluetoothStateService;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BluetoothModule extends ReactContextBaseJavaModule {

  private final ReactContext reactContext;
  private final ConnectionService connectionService;
  private final BluetoothStateService bluetoothStateService;

  public BluetoothModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    new ConnectionListener(getReactApplicationContext());
    new BluetoothStateListener(getReactApplicationContext());
    connectionService = new ConnectionService(getReactApplicationContext());
    bluetoothStateService = new BluetoothStateService(getReactApplicationContext());
  }

  @NonNull
  @Override
  public String getName() {
    return "BluetoothModule";
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  @ReactMethod
  public void listPaired(Promise promise) {
    new PairedDevices().queryPaired(promise);
  }

  @ReactMethod
  public void listUnpaired(Promise promise) {
    new DiscoverDevices(reactContext).findAllDevices(promise);
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  @ReactMethod
  public void connect(String address) {
    connectionService.connectToAddress(address);
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  @ReactMethod
  public void disconnect(String address) {
    connectionService.disconnect(address);
  }

  @ReactMethod
  public void isBluetoothActive(Promise promise) {
    bluetoothStateService.isTurnedOn(promise);
  }

  @ReactMethod
  public void requestEnable() {
    bluetoothStateService.enableBluetooth();
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  @ReactMethod
  public void isConnected(String address, Promise promise) {
    connectionService.isConnected(address, promise);
  }
}
