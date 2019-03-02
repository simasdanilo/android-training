package com.simasdanilo.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "com.simasdanilo.training.extra.MESSAGE";
    private static final int TEXT_REQUEST = 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String STATE_REPLY_VISIBLE = "reply_visible";
    private static final String STATE_REPLY_TEXT = "reply_text";

    private TextView replyHeaderTextView;
    private TextView replyTextView;
    private EditText messageEditText;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (replyHeaderTextView.getVisibility() == View.VISIBLE) {
            final String replyText = replyTextView.getText().toString();

            outState.putString(STATE_REPLY_TEXT, replyText);
            outState.putBoolean(STATE_REPLY_VISIBLE, true);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();
        Log.d(LOG_TAG, "-----------------");
        Log.d(LOG_TAG, "onCreate");
        // Restore the state.
        if (savedInstanceState != null) {
            this.restoreInstanceState(savedInstanceState);
        }
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        boolean replyIsVisible = savedInstanceState.getBoolean(STATE_REPLY_VISIBLE);

        if (replyIsVisible){
            String reply = savedInstanceState.getString(STATE_REPLY_TEXT);
            this.restoreReplyViewsState(reply);
        }

    }

    private void restoreReplyViewsState(String reply) {
        this.replyHeaderTextView.setVisibility(View.VISIBLE);
        this.replyTextView.setVisibility(View.VISIBLE);
        this.replyTextView.setText(reply);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            this.handleTextRequest(resultCode,data);
        }
    }

    private void bindViews() {
        this.messageEditText = findViewById(R.id.editText_message);
        this.replyHeaderTextView = findViewById(R.id.text_header_reply);
        this.replyTextView = findViewById(R.id.text_reply);
    }

    private void handleTextRequest(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            this.showReplyReceived(data);
        }
    }

    private void showReplyReceived(Intent data) {
        String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
        this.replyHeaderTextView.setVisibility(View.VISIBLE);
        this.replyTextView.setText(reply);
        this.replyTextView.setVisibility(View.VISIBLE);
    }

    private String getMessageToSend(){
        final String message = this.messageEditText.getText().toString();
        return message;
    }

    // ON CLICK -----------------------------------------

    public void onClickSend(View view) {
        String message = this.getMessageToSend();

        Intent messageIntent = new Intent(this, SecondActivity.class);
        messageIntent.putExtra(EXTRA_MESSAGE, message);

        this.startActivityForResult(messageIntent, TEXT_REQUEST);
    }

}
