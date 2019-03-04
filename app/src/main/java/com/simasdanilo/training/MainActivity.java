package com.simasdanilo.training;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mHelloTextView;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();

        // restore saved instance state (the text color)
        if (savedInstanceState != null) {
            this.restoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final int helloTextColor = mHelloTextView.getCurrentTextColor();
        outState.putInt("color", helloTextColor);
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        final int textColor = savedInstanceState.getInt("color");
        this.mHelloTextView.setTextColor(textColor);
    }

    private void bindViews() {
        this.mHelloTextView = findViewById(R.id.hello_textview);
    }

    private String getRandomColorName() {
        Random random = new Random();
        int bound = this.mColorArray.length;
        int colorIndex = random.nextInt(bound);
        String colorName = this.mColorArray[colorIndex];

        return colorName;
    }

    private int getColor(String colorName){
        int colorResourceName = getResources().getIdentifier(colorName,
                "color", getApplicationContext().getPackageName());
        int color =  ContextCompat.getColor(this, colorResourceName);

        return color;
    }

    public void onClickChangeColor(View view) {
        String colorName = this.getRandomColorName();
        int color = this.getColor(colorName);

        this.mHelloTextView.setTextColor(color);
    }

}
