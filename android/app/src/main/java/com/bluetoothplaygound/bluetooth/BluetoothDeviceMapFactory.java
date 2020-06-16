package com.bluetoothplaygound.bluetooth;

import android.bluetooth.BluetoothDevice;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.Set;

public class BluetoothDeviceMapFactory {

  public static WritableMap queryWritableMapFromDevice(BluetoothDevice device) {
    WritableMap params = Arguments.createMap();
    params.putString("name", device.getName());
    params.putString("address", device.getAddress());
    return params;
  }

  public static WritableArray queryWritableMapFromDevice(Set<BluetoothDevice> devices) {
    WritableArray params = Arguments.createArray();
    WritableMap map = Arguments.createMap();
    for (BluetoothDevice device : devices) {
      map.putString("name", device.getName());
      map.putString("address", device.getAddress());
    }
    params.pushMap(map);
    return params;
  }
}
