import { NativeModules } from 'react-native';

const { ScreenLockModule } = NativeModules;

export default {
    requestDeviceAdmin: () => ScreenLockModule.requestDeviceAdmin(),
    lockNow: () => ScreenLockModule.lockNow(),
    setAlarmToWakeUp: (delay) => ScreenLockModule.setAlarmToWakeUp(delay),
};
