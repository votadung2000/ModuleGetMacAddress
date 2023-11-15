package com.modulegetmacaddress;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

public class MacAddressInfoModule extends ReactContextBaseJavaModule {
    MacAddressInfoModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "MacAddressInfoModule";
    }

    @ReactMethod
    public void getMacAddressInfo() {
        Toast.makeText(getReactApplicationContext(), "This is my Toast message!",
                Toast.LENGTH_LONG).show();
    }
}