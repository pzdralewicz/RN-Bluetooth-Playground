import React, {useEffect} from 'react';
import {Button, NativeEventEmitter, View} from 'react-native';
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

    return () => listeners.forEach((listener) => listener.remove());
  }, []);

  const onButtonPressed = () => {
    Bluetooth.getNativeModule()

    Bluetooth.listPaired()
      .then((test) => console.log(test))
      .catch((err) => console.log(err));
  };

  return (
    <View>
      <Button title="test button" onPress={onButtonPressed} />
    </View>
  );
};
