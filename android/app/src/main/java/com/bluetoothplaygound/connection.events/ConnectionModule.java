package com.bluetoothplaygound.connection.events;

import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class ConnectionModule extends ReactContextBaseJavaModule {

  public ConnectionModule(ReactApplicationContext reactContext) {
    super(reactContext);
    Log.i("BluetoothListener", "Startuje nasłuchiwanie");
    IntentFilter filter = new IntentFilter();
    filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
    reactContext.registerReceiver(new ConnectionListener(getReactApplicationContext()).mReceiver, filter);
  }

  @Override
  public String getName() {
    return "pairedDeviceConnectingEvents";
  }
}
