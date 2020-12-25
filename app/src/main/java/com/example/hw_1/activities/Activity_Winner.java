package com.example.hw_1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hw_1.R;

import static com.example.hw_1.activities.GameManager.p1;
import static com.example.hw_1.activities.GameManager.p2;

public class Activity_Winner extends AppCompatActivity {
    private ImageView winner_IMG_player, winner_IMG_background;
    private TextView winner_LBL;
    public static final String WINNER = "winner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__winner);

        findViews();
        setImage(R.drawable.victory, winner_IMG_background);
        checkWinner();



    }

    private void findViews(){
        winner_IMG_player = (ImageView)findViewById(R.id.winner_IMG_player);
        winner_IMG_background = (ImageView)findViewById(R.id.winner_IMG_background);
        winner_LBL = (TextView)findViewById(R.id.winner_LBL);


    }

    private void checkWinner(){
        int winner = getIntent().getIntExtra(WINNER, 0);

        if(winner == p1){
            int girl = getResources().getIdentifier("girl", "drawable", getPackageName());
            setImage(girl, winner_IMG_player);
        }else if(winner == p2){
            int boy = getResources().getIdentifier("boy", "drawable", getPackageName());
            setImage(boy, winner_IMG_player);
        }
    }
    private void setImage(int id, ImageView imageView) {
        Glide
                .with(this)
                .load(id)
                .into(imageView);
    }



}