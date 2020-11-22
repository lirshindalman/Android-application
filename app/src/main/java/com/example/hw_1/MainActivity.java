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

public class MainActivity extends AppCompatActivity {
    private ImageView main_IMG_rightCard, main_IMG_leftCard;
    private Button main_BTN_war;
    private TextView main_LBL_leftScore, main_LBL_rightScore;
    private ArrayList <String> allCards = new ArrayList<>();
    private List<String> leftPlayerCards;
    private List<String> rightPlayerCards;
    private int counter = 0;
    private int leftScore = 0;
    private int rightScore = 0;
    private String winner= " ";
    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        createArray();
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


    private void createArrayByShape(String s){
        for(int i=2; i<15; i++){
            allCards.add("card_" + s + i);
        }
    }

    private void createArray(){
        createArrayByShape("a");
        createArrayByShape("b");
        createArrayByShape("c");
        createArrayByShape("d");
        Collections.shuffle(allCards);
        leftPlayerCards = allCards.subList(0,26);
        rightPlayerCards = allCards.subList(26,52);
    }


    private void increaseScore(int leftCard, int rightCard ){
        if(leftCard > rightCard){
            leftScore++;
            main_LBL_leftScore.setText(String.valueOf(leftScore));
        } else if(rightCard > leftCard){
            rightScore++;
            main_LBL_rightScore.setText(String.valueOf(rightScore));
        }else{
            leftScore++;
            rightScore++;
            main_LBL_leftScore.setText(String.valueOf(leftScore));
            main_LBL_rightScore.setText(String.valueOf(rightScore));
        }
    }

    private void checkWinner(){
        if(leftScore > rightScore)
            winner = "boy";
          else
            winner = "girl";
    }

    private void OneRoundOfGame(int counter){
        int rightImage = getResources().getIdentifier(rightPlayerCards.get(counter), "drawable", getPackageName());
        main_IMG_rightCard.setImageResource(rightImage);

        int leftImage = getResources().getIdentifier(leftPlayerCards.get(counter), "drawable", getPackageName());
        main_IMG_leftCard.setImageResource(leftImage);

        //update score
        String s1 = leftPlayerCards.get(counter).substring(6);
        int leftCard = Integer.parseInt(s1);

        String s2 = rightPlayerCards.get(counter).substring(6);
        int rightCard = Integer.parseInt(s2);

        increaseScore(leftCard, rightCard);
    }

    private void game(){
        if(counter<26){
            OneRoundOfGame(counter);
            if(counter==25)
                main_BTN_war.setText("END GAME");
            counter++;
        }
        else{
            if(leftScore == rightScore){
                main_BTN_war.setText("Tie Breaker");
                r = new Random();
                int randNumber = r.nextInt(25) + 1;
                OneRoundOfGame(randNumber);
            }
            else{
                checkWinner();
                Intent myIntent = new Intent(MainActivity.this, Activity_Winner.class);
                myIntent.putExtra(Activity_Winner.WINNER, winner);
                startActivity(myIntent);
                finish();
            }
        }
    }

}