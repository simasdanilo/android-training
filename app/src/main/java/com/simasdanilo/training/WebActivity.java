package com.simasdanilo.training;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {
    private  TextView uriMessageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        this.bindViews();

        Intent intent = getIntent();
        Uri uri = intent.getData();

        if (uri != null) {
            this.showUri(uri);
        }
    }

    private void bindViews() {
        this.uriMessageText = findViewById(R.id.text_uri_message);
    }

    private void showUri(Uri uri) {
        final String uriLabel = getString(R.string.uri_label);
        final String uriText = uriLabel + uri.toString();
        this.uriMessageText.setText(uriText);
    }
}
