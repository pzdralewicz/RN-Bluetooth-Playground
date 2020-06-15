package com.bluetoothplaygound.connection.events;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;

public class ConnectionListener {

  private final ReactApplicationContext reactContext;

  public final BroadcastReceiver mReceiver =
      new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          String action = intent.getAction();
          BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
          ConnectionFacade connectionFacade = new ConnectionFacade();
          if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            Log.i("BluetoothListener", "Połączono urządzenie: " + device.getName());
            connectionFacade.connected(device, reactContext);
          } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            Log.i("BluetoothListener", "Rozłączono urządzenie: " + device.getName());
            connectionFacade.disconnected(device, reactContext);
          }
        }
      };

  public ConnectionListener(ReactApplicationContext reactContext) {
    this.reactContext = reactContext;
  }
}
