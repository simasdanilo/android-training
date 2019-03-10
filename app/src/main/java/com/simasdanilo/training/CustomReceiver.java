package com.simasdanilo.training;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        if (intentAction != null) {
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    this.onPowerConnected(context);
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    this.onPowerDisconnected(context);
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    this.onCustomBroadcast(context);
                    break;
                default:
                    String toastMessage = "unknown intent action";
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onCustomBroadcast(Context context) {
        String toastMessage = context.getString(R.string.custom_broadcast_received);
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }

    private void onPowerConnected(Context context) {
        String toastMessage = context.getString(R.string.power_connected);
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }

    private void onPowerDisconnected(Context context){
        String toastMessage = context.getString(R.string.power_disconnected);
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
