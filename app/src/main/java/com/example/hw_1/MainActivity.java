package com.example.hw_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView v_right_card, v_left_card;
    private Button b_war;
    private TextView t_left_score, t_right_score;
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
        Collections.shuffle(allCards);
        leftPlayerCards = allCards.subList(0,26);
        rightPlayerCards = allCards.subList(26,52);
        r = new Random();
        initViews();

    }

    private void initViews() {

        b_war.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<26){
                    game(counter);
                    if(counter==25)
                        b_war.setText("END GAME");
                    counter++;
                }
                else{
                    if(leftScore == rightScore){
                        b_war.setText("TEKO");
                        int randNumber = r.nextInt(25) + 1;
                        game(randNumber);
                    }
                    else{
                        checkWinner();
                        Intent myIntent = new Intent(MainActivity.this, Activity_Winner.class);
                        myIntent.putExtra(Activity_Winner.WINNER, winner);
                        startActivity(myIntent);
                    }
                }
            }
        });
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
    }

    private void findViews(){
        v_right_card = (ImageView)findViewById(R.id.v_right_card);
        v_left_card = (ImageView)findViewById(R.id.v_left_card);
        b_war = (Button)findViewById(R.id.b_war);
        t_left_score = (TextView)findViewById(R.id.t_left_score);
        t_right_score = (TextView)findViewById(R.id.t_right_score);


    }

    private void increaseScore(int leftCard, int rightCard ){
        if(leftCard > rightCard){
            leftScore++;
            t_left_score.setText(String.valueOf(leftScore));
        } else if(rightCard > leftCard){
            rightScore++;
            t_right_score.setText(String.valueOf(rightScore));
        }else{
            leftScore++;
            rightScore++;
            t_left_score.setText(String.valueOf(leftScore));
            t_right_score.setText(String.valueOf(rightScore));
        }
    }

    private void checkWinner(){
        if(leftScore > rightScore)
            winner = "boy";
          else
            winner = "girl";
    }

    private void game(int counter){
        int rightImage = getResources().getIdentifier(rightPlayerCards.get(counter), "drawable", getPackageName());
        v_right_card.setImageResource(rightImage);

        int leftImage = getResources().getIdentifier(leftPlayerCards.get(counter), "drawable", getPackageName());
        v_left_card.setImageResource(leftImage);

        //update score
        String s1 = leftPlayerCards.get(counter).substring(6);
        int leftCard = Integer.parseInt(s1);

        String s2 = rightPlayerCards.get(counter).substring(6);
        int rightCard = Integer.parseInt(s2);

        increaseScore(leftCard, rightCard);
    }

}