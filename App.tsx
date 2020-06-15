import React from 'react';
import {SafeAreaView, StyleSheet} from 'react-native';
import {BTListener} from './src/components/BTListener';

const App = () => {
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
