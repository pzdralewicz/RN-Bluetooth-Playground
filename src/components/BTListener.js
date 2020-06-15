import React, {useEffect} from 'react';
import {NativeModules, NativeEventEmitter, Text, View} from 'react-native';

export const BTListener = () => {
  useEffect(() => {
    const emitter = new NativeEventEmitter(
      NativeModules.BluetoothModule,
    );
    const listeners = [
      emitter.addListener('CONNECTED', (device) => {
        console.log('CONNECT', device);
      }),
      emitter.addListener('DISCONNECTED', (device) => {
        console.log('DISCONNECT', device);
      }),
      emitter.addListener('TURNED_ON', () => {
        console.log('TURNED_ON');
      }),
      emitter.addListener('TURNED_OFF', () => {
        console.log('TURNED_OFF');
      }),
      emitter.addListener('ERROR', () => {
        console.log('TURNED_OFF');
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
