import styled from 'styled-components';
import {Text, View, ScrollView as RNScrollView, TouchableOpacity} from 'react-native';

export const ScrollView = styled(RNScrollView)`
  width: 100%;
  flex: 1;
`

export const StatusBar = styled(TouchableOpacity)`
  background: blanchedalmond;
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
`

export const StatusText = styled(Text)`
  
`

export const DevicesContainer = styled(View)`
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
`

export const Title = styled(Text)`
  margin: 10px;
`

export const List = styled(View)`
  width: 100%;
`

export const ItemContainer = styled(TouchableOpacity)`
  background: #ddd;
  width: 100%;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
`
