package com.simasdanilo.training;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotificationManager;
    private ToggleButton alarmToggle;
    private Intent notifyIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.createNotifyIntent();
        this.createNotificationChannel();
        this.setupNotificationManager();
        this.setupAlarmToggle();

    }

    private void createNotifyIntent() {
        this.notifyIntent = new Intent(this, AlarmReceiver.class);
    }
    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {
        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void setupNotificationManager() {
        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void setupAlarmToggle() {
        this.alarmToggle = findViewById(R.id.alarmToggle);

        boolean isAlarmOn = this.isAlarmOn();
        this.alarmToggle.setChecked(isAlarmOn);

        this.alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton,
                                                 boolean isChecked) {
                        onAlarmToggled(isChecked);
                    }
                });
    }

    private boolean isAlarmOn() {
        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                this.notifyIntent, PendingIntent.FLAG_NO_CREATE);

        boolean isAlarmOn = notifyPendingIntent != null;

        return isAlarmOn;
    }

    private void onAlarmToggled(boolean isChecked) {

        final AlarmManager alarmManager = (AlarmManager) getSystemService
                (ALARM_SERVICE);

        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, this.notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String toastMessage;

        if(isChecked){
            this.turnAlarmOn(alarmManager, notifyPendingIntent);
            toastMessage = getString(R.string.alarm_on_message);
        } else {
            this.turnAlarmOff(alarmManager, notifyPendingIntent);
            toastMessage = getString(R.string.alarm_off_message);
        }

        this.displayToast(toastMessage);
    }

    private void displayToast(String toastMessage) {
        //Show a toast to say the alarm is turned on or off.
        Toast.makeText(MainActivity.this, toastMessage,Toast.LENGTH_SHORT)
                .show();
    }

    private void turnAlarmOff(AlarmManager alarmManager, PendingIntent notifyPendingIntent) {
        //Cancel notification if the alarm is turned off
        this.mNotificationManager.cancelAll();
        if (alarmManager != null) {
            alarmManager.cancel(notifyPendingIntent);
        }
    }

    private void turnAlarmOn(AlarmManager alarmManager, PendingIntent notifyPendingIntent) {
        long TEN_SECONDS = 10 * 1000;
        long repeatInterval = TEN_SECONDS; // @TESTE AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        long triggerTime = SystemClock.elapsedRealtime()
                + repeatInterval;

        //Set the repeating alarm with a 15 minute interval
        if (alarmManager != null) {
            alarmManager.setInexactRepeating
                    (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            triggerTime, repeatInterval, notifyPendingIntent);
        }
    }

}
