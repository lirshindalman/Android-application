package com.example.hw_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_Winner extends AppCompatActivity {
    private ImageView winner_IMG_player;
    private TextView winner_LBL;
    public static final String WINNER = "WINNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__winner);

        findViews();
        String winner = getIntent().getStringExtra(WINNER);

        if(winner.equals("boy")){
            int boy = getResources().getIdentifier("boy", "drawable", getPackageName());
            winner_IMG_player.setImageResource(boy);
        }else if(winner.equals("girl")){
            int girl = getResources().getIdentifier("girl", "drawable", getPackageName());
            winner_IMG_player.setImageResource(girl);
        }


    }

    private void findViews(){
        winner_IMG_player = (ImageView)findViewById(R.id.winner_IMG_player);
        winner_LBL = (TextView)findViewById(R.id.winner_LBL);


    }




}