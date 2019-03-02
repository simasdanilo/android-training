package com.simasdanilo.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "com.simasdanilo.training.extra.MESSAGE";
    private static final int TEXT_REQUEST = 1;

    private TextView replyHeaderText;
    private TextView replyText;
    private EditText messageEditText;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            this.handleTextRequest(resultCode,data);
        }
    }

    private void handleTextRequest(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            this.showReplyReceived(data);
        }
    }

    private void showReplyReceived(Intent data) {
        String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
        this.replyHeaderText.setVisibility(View.VISIBLE);
        this.replyText.setText(reply);
        this.replyText.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();
    }

    private void bindViews() {
        this.messageEditText = findViewById(R.id.editText_message);
        this.replyHeaderText = findViewById(R.id.text_header_reply);
        this.replyText = findViewById(R.id.text_reply);
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
