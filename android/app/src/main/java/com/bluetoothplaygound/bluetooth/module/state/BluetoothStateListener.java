package com.bluetoothplaygound.bluetooth.module.state;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.react.bridge.ReactApplicationContext;

public class BluetoothStateListener {

  private final ReactApplicationContext reactContext;
  public final BroadcastReceiver mReceiver =
      new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          BluetoothStateService bluetoothStateService = new BluetoothStateService();
          if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF) {
            bluetoothStateService.turnedOff(reactContext);
          } else if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
              == BluetoothAdapter.STATE_ON) {
            bluetoothStateService.turnedOn(reactContext);
          }
        }
      };

  public BluetoothStateListener(ReactApplicationContext reactContext) {
    this.reactContext = reactContext;
    registerBluetoothReceiver();
  }

  private void registerBluetoothReceiver() {
    IntentFilter filter = new IntentFilter();
    filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
    reactContext.registerReceiver(mReceiver, filter);
  }
}
