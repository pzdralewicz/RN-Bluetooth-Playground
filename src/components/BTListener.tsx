import React, {useEffect} from 'react';
import {NativeEventEmitter, Text, View} from 'react-native';
import BluetoothModule from '../modules/Bluetooth';

export const BTListener = () => {
  const Bluetooth = BluetoothModule.getInstance();

  useEffect(() => {
    const emitter = new NativeEventEmitter(Bluetooth.getNativeModule());

    const listeners = [
      emitter.addListener('CONNECTED', (device) => {
        console.log('CONNECT', device);
      }),
      emitter.addListener('DISCONNECTED', (device) => {
        console.log('DISCONNECT', device);
      }),
    ];

    return () => listeners.forEach(listener => listener.remove());
  }, []);



  return (
    <View>
      <Text>test</Text>
    </View>
  );
};
