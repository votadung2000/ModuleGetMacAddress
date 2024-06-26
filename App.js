import React from 'react'
import { StyleSheet, Button, View } from 'react-native'
import { getMacAddress } from 'react-native-device-info';

import { MacAddressInfo, LockScreenModule } from './src/components'

const App = () => {

  const requestDeviceAdmin = () => {
    LockScreenModule.requestDeviceAdmin();
  }

  const lockNow = () => {
    LockScreenModule.lockNow();
    LockScreenModule.setAlarmToWakeUp(3000);
  }

  const onPress = () => {
    let response = MacAddressInfo.getMacAddressInfo();
    console.log("response", response)
  };

  const onPressLib = async () => {
    let response = await getMacAddress();
    console.log("response", response)
  }

  return (
    <View style={styles.container}>
      <Button
        title="Module"
        color="#841584"
        onPress={onPress}

      />
      <View style={styles.vwLine} />
      <Button
        title="Library"
        color="#841584"
        onPress={onPressLib}
      />
      <View style={styles.vwLine} />
      <Button
        title="Request device admin"
        color="#841584"
        onPress={requestDeviceAdmin}
      />
      <View style={styles.vwLine} />
      <Button
        title="Lock now"
        color="#841584"
        onPress={lockNow}
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
  vwLine: {
    marginBottom: 10,
    marginTop: 10,
  }
})

export default App;