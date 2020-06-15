export interface BluetoothModule {
  enableBluetooth: () => Promise<boolean>;
  disableBluetooth: () => Promise<boolean>;
  requestEnable: () => Promise<boolean>;
  isBluetoothActive: () => Promise<boolean>;

  connect: (macAddress: string) => Promise<boolean>;
  disconnect: (macAddress: string) => Promise<boolean>;
  isConnected: (macAddress: string) => Promise<boolean>;

  pair: (macAddress: string) => Promise<boolean>;

  listPaired: () => Promise<Device[]>;
  listUnpaired: () => Promise<Device[]>;

  isBLE: (macAddress: string) => Promise<boolean>;
}

export interface Device {

}
