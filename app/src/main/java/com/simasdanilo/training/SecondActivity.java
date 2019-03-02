package com.simasdanilo.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.simasdanilo.training.extra.REPLY";
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    private EditText replyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.bindViews();
        this.showMessageReceived();
        Log.d(LOG_TAG, "-------------------");
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    private void bindViews() {
        this.replyEditText = findViewById(R.id.editText_reply);
    }

    private String getMessageReceived() {
        Intent intent = getIntent();
        final String messageReceived = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        return messageReceived;
    }

    private void showMessageReceived() {
        String message = this.getMessageReceived();
        TextView messageText = findViewById(R.id.text_message);
        messageText.setText(message);
    }

    private Intent createReplyIntent(){
        String reply = this.replyEditText.getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);

        return replyIntent;
    }

    // onClick

    public void onClickReply(View view) {
        Intent replyIntent = this.createReplyIntent();

        this.setResult(RESULT_OK,replyIntent);
        this.finish();
        Log.d(LOG_TAG, "End SecondActivity");
    }
}

