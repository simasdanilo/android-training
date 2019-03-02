package com.simasdanilo.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.simasdanilo.training.extra.REPLY";

    private EditText replyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.bindViews();
        this.showMessageReceived();
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
    }
}

