package com.simasdanilo.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_STATE = "currentText";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindTextView();
        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null) {
            this.restoreInstanceState(savedInstanceState);
        }
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        String text = savedInstanceState.getString(TEXT_STATE);
        this.mTextView.setText(text);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        String text =  mTextView.getText().toString();
        outState.putString(TEXT_STATE,text);
    }

    private void bindTextView() {
        this.mTextView = findViewById(R.id.textView1);
    }

    public void onClickStartTask(View view) {
        // Put a message in the text view
        this.mTextView.setText(R.string.napping);

        // Start the AsyncTask.
        new SimpleAsyncTask(this.mTextView).execute();
    }



}
