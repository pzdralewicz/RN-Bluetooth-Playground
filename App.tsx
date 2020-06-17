import React, {useEffect} from 'react';
import {SafeAreaView, StyleSheet} from 'react-native';
import {BTListener} from './src/components/BTListener';
import {requestBTPermissions} from './src/components/helpers';

const App = () => {
  useEffect(() => {
    requestBTPermissions();
  });

  return (
    <>
      <SafeAreaView style={styles.Container}>
        <BTListener />
      </SafeAreaView>
    </>
  );
};

const styles = StyleSheet.create({
  Container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default App;
