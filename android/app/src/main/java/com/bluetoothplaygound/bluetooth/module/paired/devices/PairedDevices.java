package com.bluetoothplaygound.bluetooth.module.paired.devices;

import static com.bluetoothplaygound.bluetooth.BluetoothDeviceMapFactory.queryWritableMapFromDevice;

import android.bluetooth.BluetoothAdapter;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;

public class PairedDevices {

  private final BluetoothAdapter bluetoothAdapter;

  public PairedDevices() {
    this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
  }

  @ReactMethod
  void queryDevices(Promise promise) {
    WritableArray params = queryWritableMapFromDevice(bluetoothAdapter.getBondedDevices());
    promise.resolve(params);
  }
}
