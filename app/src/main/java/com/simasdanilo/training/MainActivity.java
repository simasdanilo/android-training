package com.simasdanilo.training;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowAlert(View view) {
        AlertDialog.Builder alertBuilder =
                new AlertDialog.Builder(MainActivity.this);

        // Set the dialog title and message.
        alertBuilder.setTitle(R.string.alert_title);
        alertBuilder.setMessage(R.string.alert_message);

        // Add the dialog buttons.
        alertBuilder.setPositiveButton(R.string.ok_button, this.getPositiveOnClickListener() );
        alertBuilder.setNegativeButton(R.string.cancel_button, this.getNegativeOnClickListener());

        // Create and show the AlertDialog.
        alertBuilder.show();
    }

    private DialogInterface.OnClickListener getPositiveOnClickListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User clicked OK button.
                Toast.makeText(getApplicationContext(),
                        R.string.pressed_ok,
                        Toast.LENGTH_SHORT).show();
            }
        };
    }

    private DialogInterface.OnClickListener getNegativeOnClickListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User cancelled the dialog.
                Toast.makeText(getApplicationContext(),
                        R.string.pressed_cancel,
                        Toast.LENGTH_SHORT).show();
            }
        };
    }
}
