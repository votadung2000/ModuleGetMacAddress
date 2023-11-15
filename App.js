import React from 'react'
import { StyleSheet, Button, View } from 'react-native'

import { MacAddressInfo } from './src/components'

const App = () => {
  const onPress = () => {
    MacAddressInfo.getMacAddressInfo();
  };

  return (
    <View style={styles.container}>
      <Button
        title="Click to invoke your native module!"
        color="#841584"
        onPress={onPress}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  }
})

export default App;