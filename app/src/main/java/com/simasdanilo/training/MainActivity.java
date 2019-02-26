package com.simasdanilo.training;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private TextView countText;
    private Button countButton;
    private Button zeroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindViews();
    }

    private void bindViews(){
        this.countText = findViewById(R.id.show_count);
        this.countButton = findViewById(R.id.button_count);
        this.zeroButton = findViewById(R.id.button_zero);
    }

    // helpers ---------------------------------------------

    private boolean isEven(int number){
        return number%2 == 0;
    }

    // general ---------------------------------------------

    private void setCount(int newCount){
        this.count = newCount;
        this.onCountChanged();
    }

    private void onCountChanged(){
        this.updateCountText();
        this.updateCountButtonBackground();
        this.updateZeroButtonBackground();
    }

    // update views -----------------------------------------

    private void updateCountText(){
        String countString = Integer.toString(this.count);
        this.countText.setText(countString);
    }

    private void updateCountButtonBackground(){
        boolean countIsEven = this.isEven(this.count);

        if (countIsEven){
            this.countButton.setBackgroundColor(Color.BLUE);
        }

        if (!countIsEven){
            this.countButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    private void updateZeroButtonBackground(){
        boolean countIsZero = this.count == 0;

        if (countIsZero){
            this.zeroButton.setBackgroundColor(Color.GRAY);
        }

        if (!countIsZero){
            this.zeroButton.setBackgroundColor(Color.GREEN);
        }
    }

    // onClicks ----------------------------------------

    public void onClickToast(View view) {
        Toast toast = Toast.makeText(this,R.string.toast_message,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickCount(View view) {
        int countUp = this.count+1;
        this.setCount(countUp);
    }

    public void onClickZero(View view) {
        this.setCount(0);
    }
}
