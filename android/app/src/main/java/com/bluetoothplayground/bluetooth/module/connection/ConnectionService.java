package com.bluetoothplayground.bluetooth.module.connection;

import static com.bluetoothplayground.bluetooth.BluetoothDeviceMapFactory.queryWritableMapFromDevice;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;
import com.bluetoothplayground.bluetooth.EventDispatcher;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
public class ConnectionService extends BluetoothGattCallback {

  private final EventDispatcher eventDispatcher = new EventDispatcher();
  private final ReactApplicationContext reactContext;
  private Map<String, BluetoothGatt> bluetoothGattMap = new HashMap<>();

  public ConnectionService(ReactApplicationContext reactContext) {
    this.reactContext = reactContext;
  }

  public void connected(BluetoothDevice device) {
    eventDispatcher.sendEvent(
        reactContext, Status.CONNECTED.name(), queryWritableMapFromDevice(device));
  }

  public void disconnected(BluetoothDevice device) {
    eventDispatcher.sendEvent(
        reactContext, Status.DISCONNECTED.name(), queryWritableMapFromDevice(device));
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  public void connectToAddress(String address) {
    BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
    bluetoothGattMap.put(
        device.getAddress(),
        device.connectGatt(reactContext, true, new BluetoothGattCallback() {}));
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  public void disconnect(String address) {
    BluetoothGatt bluetoothGatt = bluetoothGattMap.get(address);
    if (bluetoothGatt != null) bluetoothGatt.disconnect();
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  public void isConnected(String address, Promise promise) {
    BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
    promise.resolve(isConnectedBLE(device, reactContext) || isConnectedClassic(device));
  }

  private boolean isConnectedClassic(BluetoothDevice device) {
    try {
      Method m = device.getClass().getMethod("isConnected", (Class[]) null);
      return (boolean) m.invoke(device, (Object[]) null);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
  private boolean isConnectedBLE(BluetoothDevice device, ReactContext reactContext) {
    BluetoothManager bm =
        (BluetoothManager) reactContext.getSystemService(Context.BLUETOOTH_SERVICE);
    List<BluetoothDevice> devices = bm.getConnectedDevices(BluetoothGatt.GATT);
    return devices.contains(device);
  }
}
