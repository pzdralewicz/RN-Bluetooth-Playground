import {NativeModulesStatic} from 'react-native';

export interface BluetoothNativeModule {
  enableBluetooth: () => Promise<boolean>;
  disableBluetooth: () => Promise<boolean>;
  requestEnable: () => Promise<boolean>;
  isBluetoothActive: () => Promise<boolean>;

  connect: (macAddress: string) => Promise<Device | BTError>;
  disconnect: (macAddress: string) => Promise<boolean | BTError>;
  isConnected: (macAddress: string) => Promise<boolean>;

  pair: (macAddress: string) => Promise<Device | BTError>;

  listPaired: () => Promise<Device[]>;
  listUnpaired: () => Promise<Device[]>;

  isBLE: (macAddress: string) => Promise<boolean>;
}

export interface BluetoothBridge extends BluetoothNativeModule {
  getNativeModule: () => NativeModulesStatic;
}

export interface Device {
  address: string;
  name: string;
}

export interface BTError {
  message: string;
}
