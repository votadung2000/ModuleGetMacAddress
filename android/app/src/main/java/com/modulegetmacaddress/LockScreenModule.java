package com.modulegetmacaddress;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class LockScreenModule extends ReactContextBaseJavaModule {
    private DevicePolicyManager devicePolicyManager;
    private ComponentName compName;

    public LockScreenModule(ReactApplicationContext reactContext) {
        super(reactContext);
        devicePolicyManager = (DevicePolicyManager) reactContext.getSystemService(Context.DEVICE_POLICY_SERVICE);
        compName = new ComponentName(reactContext, MyDeviceAdminReceiver.class);
    }

    @Override
    public String getName() {
        return "ScreenLockModule";
    }

    @ReactMethod
    public void requestDeviceAdmin(Promise promise) {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "This app needs device admin permission to lock the screen.");
            activity.startActivityForResult(intent, 1);
            promise.resolve(null);
        } else {
            promise.reject("E_ACTIVITY_NOT_AVAILABLE", "Activity is not available");
        }
    }

    @ReactMethod
    public void lockNow(Promise promise) {
        if (devicePolicyManager.isAdminActive(compName)) {
            devicePolicyManager.lockNow();
            promise.resolve(null);
        } else {
            promise.reject("E_DEVICE_ADMIN_NOT_ENABLED", "Device admin is not enabled");
        }
    }

    @ReactMethod
    public void setAlarmToWakeUp(int delayInMillis) {
        Context context = getReactApplicationContext();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long triggerAtMillis = System.currentTimeMillis() + delayInMillis;
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }

}
