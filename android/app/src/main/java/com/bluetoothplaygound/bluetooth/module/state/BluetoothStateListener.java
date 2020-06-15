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
          BluetoothStateFacade bluetoothStateFacade = new BluetoothStateFacade();
          if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF) {
            bluetoothStateFacade.turnedOff(reactContext);
          } else if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1)
              == BluetoothAdapter.STATE_ON) {
            bluetoothStateFacade.turnedOn(reactContext);
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
