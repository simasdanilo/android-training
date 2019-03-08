package com.simasdanilo.training;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, Response> {
    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    FetchBook(TextView titleText, TextView authorText) {
        this.mTitleText = new WeakReference<>(titleText);
        this.mAuthorText = new WeakReference<>(authorText);
    }

    @Override
    protected Response doInBackground(String... strings) {
        String query = strings[0];

        Response findBookResponse = BookService.findBook(query);

        return findBookResponse;
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);

        if (response.ok) {
            Book book = (Book) response.data;
            this.handleResponseSuccess(book);
        } else {
            String error = response.error;
            this.handleResponseFailed(error);
        }
    }

    private void handleResponseFailed(String error) {
        mTitleText.get().setText(error);
        mAuthorText.get().setText("");
    }

    private void handleResponseSuccess(Book book) {
        final String title = book.getTitle();
        final String authors = book.getAuthors();

        mTitleText.get().setText(title);
        mAuthorText.get().setText(authors);
    }

}
