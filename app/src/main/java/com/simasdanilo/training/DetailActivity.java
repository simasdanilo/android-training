package com.simasdanilo.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        this.setupViews();
    }

    private void setupViews() {
        TextView sportsTitle = findViewById(R.id.titleDetail);
        ImageView sportsImage = findViewById(R.id.sportsImageDetail);

        String title = getIntent().getStringExtra("title");
        int imageResource = getIntent().getIntExtra("image_resource",0);

        sportsTitle.setText(title);
        Glide.with(this).load(imageResource).into(sportsImage);
    }
}
