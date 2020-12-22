package com.example.hw_1.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.hw_1.R;

public class Activity_welcome extends AppCompatActivity {
    private Button welcome_BTN_start;
    private Button welcome_BTN_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViews();
        initViews();


    }

    private void findViews(){
        welcome_BTN_start = (Button)findViewById(R.id.welcome_BTN_start);
        welcome_BTN_records = (Button)findViewById(R.id.welcome_BTN_records);

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
}