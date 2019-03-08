package com.simasdanilo.training;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();
    }

    private void bindViews() {
        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
    }

    private void hideKeyboard(View view){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void onClickSearchBooks(View view) {
        this.hideKeyboard(view);

        String query = this.getQuery();

        boolean isConnected = NetworkHelper.isConnected(this);
        if (!isConnected){
            this.showNoConnection();
            return;
        }

        boolean queryIsEmpty = query.length() == 0;
        if (queryIsEmpty){
            this.showNoSearchTerm();
            return;
        }

        this.showLoading();
        new FetchBook(mTitleText, mAuthorText).execute(query);
    }

    private String getQuery() {
        return mBookInput.getText().toString();
    }

    private void showLoading() {
        mAuthorText.setText("");
        mTitleText.setText(R.string.loading);
    }

    private void showNoSearchTerm() {
        mAuthorText.setText("");
        mTitleText.setText(R.string.no_search_term);
    }

    private void showNoConnection() {
        mAuthorText.setText("");
        mTitleText.setText(R.string.no_network);
    }

    private void showNoResults() {
        mAuthorText.setText("");
        mTitleText.setText(R.string.no_results);
    }

}
