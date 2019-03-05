package com.simasdanilo.training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.readIntent();
    }

    private void readIntent() {
        Intent intent = getIntent();
        String orderMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        this.updateOrderText(orderMessage);
    }

    private void updateOrderText(String order) {
        String message = "Order: " + order;
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);
    }

}
