import {
  NativeModules,
  NativeModulesStatic,
} from 'react-native';
import type { BluetoothNativeModule } from './interfaces/BluetoothNativeModule';

export interface BluetoothBridge extends BluetoothNativeModule {
  getNativeModule: NativeModulesStatic;
}

class Bluetooth implements BluetoothBridge {
  nativeModule = NativeModules.BluetoothModule;
  private static instance: Bluetooth;

  private constructor() {}

  public static getInstance = (): Bluetooth => {
    if (Bluetooth.instance === undefined) {
      Bluetooth.instance = new Bluetooth();
    }

    return Bluetooth.instance;
  };

  getNativeModule = this.nativeModule;

  enableBluetooth = async () => {
    return await this.nativeModule.enableBluetooth();
  };

  disableBluetooth = async () => {
    return await this.nativeModule.disableBluetooth();
  };

  requestEnable = async () => {
    return await this.nativeModule.requestEnable();
  };

  isBluetoothActive = async () => {
    return await this.nativeModule.isBluetoothActive();
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

  pair = async (macAddress: string) => {
    return await this.nativeModule.pair(macAddress);
  };

  listPaired = async () => {
    return await this.nativeModule.listPaired();
  };

  listUnpaired = async () => {
    return await this.nativeModule.listUnpaired();
  };

  isBLE = async (macAddress: string) => {
    return await this.nativeModule.isBLE(macAddress);
  };
}

export default Bluetooth;
