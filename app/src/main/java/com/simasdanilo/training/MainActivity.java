package com.simasdanilo.training;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();
        this.reconnectLoader();
    }

    private void reconnectLoader() {
        if(this.getMyLoaderManager().getLoader(0)!=null){
            this.getMyLoaderManager().initLoader(0,null,this);
        }
    }

    private void bindViews() {
        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
    }

    public void onClickSearchBooks(View view) {

        // Get the search string from the input field.
        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }


        if (NetworkHelper.isOnline(this) && queryString.length() != 0) {
            // @AsyncTask new FetchBook(mTitleText, mAuthorText).execute(queryString);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            this.getMyLoaderManager().restartLoader(0, queryBundle, this);

            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        }else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            } else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }

    private android.support.v4.app.LoaderManager getMyLoaderManager() {
        return android.support.v4.app.LoaderManager.getInstance(this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle bundle) {
        //loader is being created
        String queryString = "";

        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }

        return new BookLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        // when the data has finished loading
        // This is where you add the code to update your UI with the results.

        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(data);
            // Get the JSONArray of book items.
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            // Initialize iterator and results fields.
            int i = 0;
            String title = null;
            String authors = null;

            // Look for results in the items array, exiting
            // when both the title and author
            // are found or when all items have been checked.
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }

            // If both are found, display the result.
            if (title != null && authors != null) {
                mTitleText.setText(title);
                mAuthorText.setText(authors);
            } else {
                // If none are found, update the UI to
                // show failed results.
                mTitleText.setText(R.string.no_results);
                mAuthorText.setText("");
            }

        } catch (Exception e) {
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            mTitleText.setText(R.string.no_results);
            mAuthorText.setText("");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // when the loader is reset. cleans up any remaining resources.
    }
}
