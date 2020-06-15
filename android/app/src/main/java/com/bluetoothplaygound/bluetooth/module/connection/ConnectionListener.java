package com.bluetoothplaygound.bluetooth.module.connection;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.react.bridge.ReactApplicationContext;

public class ConnectionListener {

  private final ReactApplicationContext reactContext;

  public final BroadcastReceiver mReceiver =
      new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          String action = intent.getAction();
          BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

          ConnectionService connectionService = new ConnectionService();
          if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            connectionService.connected(device, reactContext);
          } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            connectionService.disconnected(device, reactContext);
          }
        }
      };

  public ConnectionListener(ReactApplicationContext reactContext) {
    this.reactContext = reactContext;
    registerBluetoothReceiver();
  }

  private void registerBluetoothReceiver() {
    IntentFilter filter = new IntentFilter();
    filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
    reactContext.registerReceiver(mReceiver, filter);
  }
}
