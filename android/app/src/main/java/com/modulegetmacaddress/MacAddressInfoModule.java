package com.modulegetmacaddress;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class MacAddressInfoModule extends ReactContextBaseJavaModule {
    private static final String TAG = "NetworkCallbackExample";
    MacAddressInfoModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "MacAddressInfoModule";
    }

    @ReactMethod
    public String getMacAddressInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getReactApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);

                    Log.d(TAG, "Network is available");

                    LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                    if (linkProperties != null) {
                        Log.d(TAG, "Link Properties: " + String.valueOf(linkProperties));

                        // Access the routes
//                        for (LinkProperties.RouteInfo routeInfo : linkProperties.getRoutes()) {
//                            Log.d(TAG, "Route: " + routeInfo.toString());
//                        }
                    }
                }

                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    Log.d(TAG, "Network is lost");
                }
            };

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(networkCallback);
            } else {
                // For versions below N, you can use the deprecated method
                // connectivityManager.registerNetworkCallback(request, networkCallback);
            }
        }
        return null;
    }

//        try {
//            String interfaceName = "wlan0";
//            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface inter_face : interfaces) {
//                if (!inter_face.getName().equalsIgnoreCase(interfaceName)) {
//                    continue;
//                }
//
//                byte[] mac = inter_face.getHardwareAddress();
//                if (mac == null) {
//                    return "";
//                }
//
//                StringBuilder buffer = new StringBuilder();
//                for (byte aMac : mac) {
//                    buffer.append(String.format("%02X:", aMac));
//                }
//                if (buffer.length() > 0) {
//                    buffer.deleteCharAt(buffer.length() - 1);
//                }
//                return buffer.toString();
//            }
//        } catch (Exception ignored) {
//        }
//        return "";
//    }
}
