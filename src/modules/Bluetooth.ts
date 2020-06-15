import {NativeModules} from 'react-native';
import {BluetoothModule} from '../interfaces/BluetoothNativeModule';

export default class Bluetooth {
  nativeModule = NativeModules.pairedDeviceConnectingEvents;
  private static instance: Bluetooth;

  private constructor() {}

  public static getInstance = () => {
    if (Bluetooth.instance === undefined) {
      Bluetooth.instance = new Bluetooth();
    }

    return Bluetooth.instance;
  };

  getNativeModule = () => this.nativeModule;

  enableBluetooth = async () => {
    return await this.nativeModule.enableBluetooth();
  };

  disableBluetooth = async () => {
    return await this.nativeModule.disableBluetooth();
  };

  connect = async (macAddress: string) => {
    return await this.nativeModule.connect(macAddress);
  };

  disconnect = async (macAddress: string) => {
    return await this.nativeModule.disconnect(macAddress);
  };

  isConnected = async (macAddress: string) => {
    return await this.nativeModule.isConnected(macAddress);
  };
}
