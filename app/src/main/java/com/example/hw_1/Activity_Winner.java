package com.example.hw_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_Winner extends AppCompatActivity {
    private ImageView v_player;
    private TextView t_the_winner;
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
            v_player.setImageResource(boy);
        }else if(winner.equals("girl")){
            int girl = getResources().getIdentifier("girl", "drawable", getPackageName());
            v_player.setImageResource(girl);
        }


    }

    private void findViews(){
        v_player = (ImageView)findViewById(R.id.v_player);
        t_the_winner = (TextView)findViewById(R.id.t_the_winner);


    }




}