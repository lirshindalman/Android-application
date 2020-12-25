package com.example.hw_1.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hw_1.R;

public class Activity_welcome extends AppCompatActivity {
    private Button welcome_BTN_start;
    private Button welcome_BTN_records;
    private ImageView welcome_IMG_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViews();
        initViews();
        setImage(R.drawable.cards_background, welcome_IMG_background);

    }

    private void findViews(){
        welcome_BTN_start = (Button)findViewById(R.id.welcome_BTN_start);
        welcome_BTN_records = (Button)findViewById(R.id.welcome_BTN_records);
        welcome_IMG_background = (ImageView)findViewById(R.id.welcome_IMG_background);
    }

    private void initViews() {
        welcome_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Activity_welcome.this, Activity_Main.class);
                startActivity(myIntent);
            }
        });

        welcome_BTN_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Activity_welcome.this, Activity_Winner_List.class);
                startActivity(myIntent);
            }
        });
    }

    private void setImage(int id, ImageView imageView) {
        Glide
                .with(this)
                .load(id)
                .into(imageView);
    }
}