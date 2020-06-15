import React, {useEffect} from 'react';
import {NativeModules, NativeEventEmitter, Text, View} from 'react-native';

export const BTListener = () => {
  useEffect(() => {
    const emitter = new NativeEventEmitter(
      NativeModules.pairedDeviceConnectingEvents,
    );
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
