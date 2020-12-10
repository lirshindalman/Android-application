package com.example.hw_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.hw_1.GameManager.p1;
import static com.example.hw_1.GameManager.p2;

public class MainActivity extends AppCompatActivity {
    private ImageView main_IMG_rightCard, main_IMG_leftCard;
    private Button main_BTN_war;
    private TextView main_LBL_leftScore, main_LBL_rightScore;
    private ArrayList <String> allCards = new ArrayList<>();
    private List<String> PlayerCards1;
    private List<String> PlayerCards2;
    private int counter = 0;
    private int playerScore1 = 0;
    private int playerScore2 = 0;
    private String winner= " ";
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();

    }

    private void initViews() {
        main_BTN_war.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game();
            }
        });
    }


    private void findViews(){
        main_IMG_rightCard = (ImageView)findViewById(R.id.main_IMG_rightCard);
        main_IMG_leftCard = (ImageView)findViewById(R.id.main_IMG_leftCard);
        main_BTN_war = (Button)findViewById(R.id.main_BTN_war);
        main_LBL_leftScore = (TextView)findViewById(R.id.main_LBL_leftScore);
        main_LBL_rightScore = (TextView)findViewById(R.id.main_LBL_rightScore);
    }



    private void increaseScore(GameManager manager, int player){
        if(player == p1){
            main_LBL_leftScore.setText(String.valueOf(manager.getPlayerScore1()));
        } else if(player == p2){
            main_LBL_rightScore.setText(String.valueOf(manager.getPlayerScore2()));
        }else{
            main_LBL_leftScore.setText(String.valueOf(manager.getPlayerScore1()));
            main_LBL_rightScore.setText(String.valueOf(manager.getPlayerScore2()));
        }
    }

    private void OneRoundOfGame(GameManager manager, int counter){
        int playerImage1 = getResources().getIdentifier(manager.getPlayerCards2().get(counter), "drawable", getPackageName());
        main_IMG_rightCard.setImageResource(playerImage1);

        int playerImage2 = getResources().getIdentifier(manager.getPlayerCards1().get(counter), "drawable", getPackageName());
        main_IMG_leftCard.setImageResource(playerImage2);

        //update score
        String s1 = manager.getPlayerCards1().get(counter).substring(6);
        int playerCard1 = Integer.parseInt(s1);

        String s2 = manager.getPlayerCards2().get(counter).substring(6);
        int playerCard2 = Integer.parseInt(s2);

        int player = manager.increaseScore(playerCard1, playerCard2);
        increaseScore(manager, player);

    }

    private void game(){
        GameManager manager = new GameManager();
        manager.createArray2();
        if(counter<26){
            OneRoundOfGame(manager, counter);
            if(counter==25)
                main_BTN_war.setText("END GAME");
            counter++;
        }
        else{
            if(manager.getPlayerScore1() == manager.getPlayerScore2()){
                main_BTN_war.setText("Tie Breaker");
                r = new Random();
                int randNumber = r.nextInt(25) + 1;
                OneRoundOfGame(manager, randNumber);
            }
            else{
                int winner = manager.checkWinner(manager.getPlayerScore1(), manager.getPlayerScore2());
                Intent myIntent = new Intent(MainActivity.this, Activity_Winner.class);
                myIntent.putExtra(Activity_Winner.WINNER, winner);
                startActivity(myIntent);
                finish();
            }
        }
    }

}