import React, {useEffect, useState} from 'react';
import {NativeEventEmitter, Text} from 'react-native';
import * as Styled from './BTListener.styles';
import BluetoothModule from '../modules/Bluetooth';
import {BTError, Device} from '../interfaces/BluetoothNativeModule';

export const BTListener = () => {
  const [status, setStatus] = useState('Starting status');
  const [devices, setDevices] = useState({
    paired: [] as Device[],
    unpaired: [] as Device[],
  });
  const Bluetooth = BluetoothModule.getInstance();

  useEffect(() => {
    const emitter = new NativeEventEmitter(Bluetooth.getNativeModule());

    const listeners = [
      emitter.addListener('CONNECTED', (device: Device) => {
        console.log('CONNECT', device);
        setStatus(`Connected: ${device.name}`);
      }),
      emitter.addListener('DISCONNECTED', (device: Device) => {
        console.log('DISCONNECT', device);
        setStatus(`Disconnected: ${device.name}`);
      }),
      emitter.addListener('TURNED_ON', () => {
        console.log('TURNED_ON');
        setStatus(`Bluetooth: on`);
      }),
      emitter.addListener('TURNED_OFF', () => {
        console.log('TURNED_OFF');
        setStatus(`Bluetooth: off`);
      }),
      emitter.addListener('ERROR', (err: BTError) => {
        setStatus(`Error: ${err.message}`);
      }),
    ];

    return () => listeners.forEach((listener) => listener.remove());
  }, []);

  const loadDevices = async () => {
    console.log('Load devices');
    setStatus('Loading devices');

    const paired = await Bluetooth.listPaired();
    const unpaired = await Bluetooth.listUnpaired();

    setDevices({
      paired,
      unpaired,
    });
  };

  const requestEnable = async () => {
    console.log('Request enable');
    const {isBluetoothActive, requestEnable} = Bluetooth;

    if (!(await isBluetoothActive())) requestEnable();
  };

  const onDevicePress = async ({address}: Device) => {
    console.log('onDevicePress');
    const {connect} = Bluetooth;

    connect(address);
  };

  const onDeviceLongPress = ({address}: Device) => {
    console.log('onDeviceLongPress');
    const isPaired = devices.paired.find(
      (paired) => paired.address === address,
    );

    if (!isPaired) Bluetooth.pair(address);
  };

  const renderDevices = (devices: Device[]) => {
    return devices.map(device => (
      <Styled.ItemContainer
        onPress={() => onDevicePress(device)}
        onLongPress={() => onDeviceLongPress(device)}>
        <Text>{device.name}</Text>
      </Styled.ItemContainer>
    ));
  };

  return (
    <Styled.ScrollView>
      <Styled.StatusBar onPress={loadDevices} onLongPress={requestEnable}>
        <Styled.StatusText>{status}</Styled.StatusText>
      </Styled.StatusBar>
      <Styled.DevicesContainer>
        <Styled.Title>Sparowane</Styled.Title>
        <Styled.List>{renderDevices(devices.paired)}</Styled.List>
      </Styled.DevicesContainer>
      <Styled.DevicesContainer>
        <Styled.Title>Znalezione</Styled.Title>
        <Styled.List>{renderDevices(devices.unpaired)}</Styled.List>
      </Styled.DevicesContainer>
    </Styled.ScrollView>
  );
};
