package com.example.movielibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

public class ConnectionUtil {
    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return actNetworkInfo != null && actNetworkInfo.isConnectedOrConnecting();
    }
}
