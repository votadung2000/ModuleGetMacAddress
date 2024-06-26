package com.modulegetmacaddress;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        if (powerManager != null) {
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(
                    PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE,
                    "ScreenLockModule::WakelockTag"
            );
            if (wakeLock != null) {
                wakeLock.acquire(10000); // WakeLock will be held for 10 seconds
                wakeLock.release();
            }
        }
    }
}
