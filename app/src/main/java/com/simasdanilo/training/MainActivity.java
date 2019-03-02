package com.simasdanilo.training;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    private EditText websiteEditText;
    private EditText locationEditText;
    private EditText shareTextEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();
    }

    private void bindViews() {
        this.websiteEditText = findViewById(R.id.website_edittext);
        this.locationEditText = findViewById(R.id.location_edittext);
        this.shareTextEditText = findViewById(R.id.share_edittext);
    }

    // onClick ------------------------------------------------------

    public void onClickOpenWebsite(View view) {
        String websiteUrl = websiteEditText.getText().toString();
        Uri webpage = Uri.parse(websiteUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this website intent!");
        }
    }

    public void onClickOpenLocation(View view) {
        String location = locationEditText.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this location intent!");
        }
    }

    public void onClickShareText(View view) {
        String text = this.shareTextEditText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(text)
                .startChooser();
    }
}
