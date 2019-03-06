package com.simasdanilo.training;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;

    SimpleAsyncTask (TextView tv) {
        this.mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Sleep for the random amount of time
        int milliseconds = this.generateRandomMilliseconds();

        try {
            this.publishProgress(milliseconds);
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + milliseconds + " milliseconds!";
    }

    protected void onProgressUpdate(Integer... progress) {
        final int milliseconds = progress[0];
        final String progressText = "Sleeping for " + milliseconds + " milliseconds!";
        this.mTextView.get().setText(progressText);
    }

    protected void onPostExecute(String result) {
        this.mTextView.get().setText(result);
    }


    private int generateRandomMilliseconds(){
        Random random = new Random();
        int randomNumber = random.nextInt(11);
        int milliseconds = randomNumber * 200;

        return milliseconds;
    }
}
