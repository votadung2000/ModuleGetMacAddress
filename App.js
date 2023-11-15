import React from 'react'
import { StyleSheet, Button, View } from 'react-native'
import { getMacAddress, getMacAddressSync } from 'react-native-device-info';

import { MacAddressInfo } from './src/components'

const App = () => {
  const onPress = () => {
    let response = MacAddressInfo.getMacAddressInfo();
    console.log("response", response)
  };

  const onPressLib = async () => {
    let response = await getMacAddress();
    // let response = await getMacAddressSync();
    console.log("response", response)
  }

  return (
    <View style={styles.container}>
      <Button
        title="Module"
        color="#841584"
        onPress={onPress}

      />
      <View style={styles.vwLine}/>
      <Button
        title="Library"
        color="#841584"
        onPress={onPressLib}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  vwLine:{
    marginBottom:10,
    marginTop:10,
  }
})

export default App;