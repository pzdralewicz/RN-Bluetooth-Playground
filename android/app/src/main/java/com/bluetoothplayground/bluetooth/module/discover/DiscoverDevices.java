package com.bluetoothplayground.bluetooth.module.discover;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION_CODES;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.bluetoothplayground.bluetooth.BluetoothDeviceMapFactory;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import java.util.HashSet;
import java.util.Set;

public class DiscoverDevices {

  private final ReactContext reactContext;
  private final BluetoothAdapter bluetoothAdapter;

  public DiscoverDevices(ReactContext reactContext) {
    this.reactContext = reactContext;
    this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
  }

  public void findBLEDevices(Promise promise) {
    bluetoothAdapter.startDiscovery();
    discoverDevices(promise);
  }

  private void discoverDevices(Promise promise) {
    IntentFilter intentFilter = new IntentFilter();

    intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
    intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

    final BroadcastReceiver deviceDiscoveryReceiver =
        new BroadcastReceiver() {
          final Set<BluetoothDevice> bluetoothDeviceList = new HashSet<>();

          @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
          @Override
          public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
              BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
              if (device.getType() == BluetoothDevice.DEVICE_TYPE_LE) {
                bluetoothDeviceList.add(device);
              }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
              promise.resolve(
                  BluetoothDeviceMapFactory.queryWritableMapFromDevice(bluetoothDeviceList));
              try {
                reactContext.unregisterReceiver(this);
              } catch (Exception e) {
                Log.e("BluetoothModule", "Unable to unregister receiver", e);
              }
            }
          }
        };

    reactContext.registerReceiver(deviceDiscoveryReceiver, intentFilter);
  }
}
