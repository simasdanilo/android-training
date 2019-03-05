package com.simasdanilo.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "com.simasdanilo.training.extra.MESSAGE";

    private String orderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setupViews();
    }

    private void setupViews() {
        this.setupToolbar();
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void onClickDonut(View view) {
        this.orderMessage = getString(R.string.donut_order_message);
        displayToast(this.orderMessage);
    }

    public void onClickIceCream(View view) {
        orderMessage = getString(R.string.ice_cream_order_message);
        displayToast(this.orderMessage);
    }

    public void onClickFroyo(View view) {
        this.orderMessage = getString(R.string.froyo_order_message);
        displayToast(this.orderMessage);
    }


    public void onClickOrder(View view) {
        Intent intent =
                new Intent(MainActivity.this, OrderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, this.orderMessage);
        startActivity(intent);
    }
}
