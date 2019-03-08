package com.simasdanilo.training;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkHelper {

    private static ConnectivityManager getConnectivityManager(Context context){
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManager(context);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = (networkInfo != null && networkInfo.isConnected());

        return isConnected;
    }
}
