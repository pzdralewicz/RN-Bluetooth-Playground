package com.bluetoothplayground.bluetooth.module.connection;

import static com.bluetoothplayground.bluetooth.BluetoothDeviceMapFactory.queryWritableMapFromDevice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import com.bluetoothplayground.bluetooth.EventDispatcher;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import java.io.IOException;
import java.util.UUID;

public class ConnectionService {

  private static final UUID MY_UUID = UUID.fromString("00002A00-0000-1000-8000-00805F9B34FB");
  private final EventDispatcher eventDispatcher = new EventDispatcher();
  private BluetoothSocket socket;

  public void connected(BluetoothDevice device, ReactApplicationContext reactContext) {
    eventDispatcher.sendEvent(
        reactContext, Status.CONNECTED.name(), queryWritableMapFromDevice(device));
  }

  public void disconnected(BluetoothDevice device, ReactApplicationContext reactContext) {
    eventDispatcher.sendEvent(
        reactContext, Status.DISCONNECTED.name(), queryWritableMapFromDevice(device));
  }

  public void connectToAddress(String address, Promise promise, ReactContext reactContext) {
    new Bluetooth(reactContext).connectToAddress(address);
    promise.resolve("Connected");
  }

  public void pair(String address, ReactContext reactContext) {

    new Bluetooth(reactContext).pair(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address));
  }

  private class ConnectThread extends Thread {
    public ConnectThread(BluetoothDevice device) {
      try {
        ConnectionService.this.socket = device.createRfcommSocketToServiceRecord(MY_UUID);
      } catch (IOException e) {
        Log.e("Error", e.toString());
      }
    }

    public void run() {
      BluetoothAdapter.getDefaultAdapter().cancelDiscovery();

      try {
        socket.connect();
        new Thread().start();

      } catch (IOException e) {
        Log.i("KURWA", "KURWA: " + e);
        try {
          socket.close();
        } catch (IOException closeException) {
          Log.e("Error", e.toString());
        }
      }
    }
  }
}
