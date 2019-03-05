package com.simasdanilo.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";


    // Member variables for holding the score
    private int mScore1;
    private int mScore2;

    // Member variables for holding the score
    private TextView mScoreText1;
    private TextView mScoreText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();

        if (savedInstanceState != null) {
            this.restoreInstanceState(savedInstanceState);
        }
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        this.mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
        this.mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

        //Set the score text views
        this.mScoreText1.setText(String.valueOf(mScore1));
        this.mScoreText2.setText(String.valueOf(mScore2));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the scores.
        outState.putInt(STATE_SCORE_1, this.mScore1);
        outState.putInt(STATE_SCORE_2, this.mScore2);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Change the label of the menu based on the state of the app.
        boolean inNightMode = this.isInNightMode();
        if(inNightMode){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Check if the correct item was clicked
        int itemId = item.getItemId();

        if(itemId==R.id.night_mode) {

            boolean inNightMode = this.isInNightMode();

            if(inNightMode) {
                this.disableNightMode();
            } else {
                this.enableNightMode();
            }

            recreate(); // Recreate the activity for the theme change to take effect.
        }
        return true;
    }

    private void enableNightMode() {
        AppCompatDelegate.setDefaultNightMode
                (AppCompatDelegate.MODE_NIGHT_YES);
    }

    private void disableNightMode() {
        AppCompatDelegate.setDefaultNightMode
                (AppCompatDelegate.MODE_NIGHT_NO);
    }

    private boolean isInNightMode() {
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        return nightMode == AppCompatDelegate.MODE_NIGHT_YES;
    }


    private void bindViews() {
        //Find the TextViews by ID
        this.mScoreText1 = findViewById(R.id.score_1);
        this.mScoreText2 = findViewById(R.id.score_2);
    }

    private void increaseScoreTeam1() {
        //Decrement the score and update the TextView
        this.mScore1++;
        this.mScoreText1.setText(String.valueOf(mScore1));
    }


    private void increaseScoreTeam2() {
        //Decrement the score and update the TextView
        this.mScore2++;
        this.mScoreText2.setText(String.valueOf(mScore2));
    }

    private void decreaseScoreTeam1() {
        //Decrement the score and update the TextView
        this.mScore1--;
        this.mScoreText1.setText(String.valueOf(mScore1));
    }

    private void decreaseScoreTeam2() {
        //Decrement the score and update the TextView
        this.mScore2--;
        this.mScoreText2.setText(String.valueOf(mScore2));
    }

    public void onClickDecreaseScore(View view) {
        int buttonId = view.getId();

        switch(buttonId){
            case R.id.decreaseTeam1:
                this.decreaseScoreTeam1();
                break;
            case R.id.decreaseTeam2:
                this.decreaseScoreTeam2();
                break;
            default:
                break;
        }
    }

    public void onClickIncreaseScore(View view) {
        int buttonId = view.getId();

        switch(buttonId){
            case R.id.increaseTeam1:
                this.increaseScoreTeam1();
                break;
            case R.id.increaseTeam2:
                this.increaseScoreTeam2();
                break;
            default:
                break;
        }
    }
}
