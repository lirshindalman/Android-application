package com.example.hw_1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hw_1.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.hw_1.activities.GameManager.p1;
import static com.example.hw_1.activities.GameManager.p2;

public class Activity_Main extends AppCompatActivity {
    private ImageView main_IMG_card2, main_IMG_card1, main_IMG_background;
    private Button main_BTN_war;
    private TextView main_LBL_score1, main_LBL_score2;
    private ProgressBar main_LBL_progressBar;
    private int counter = 0;
    Random r;
    private Timer carousalTimer;
    final int DELAY = 500;
    GameManager manager = new GameManager();
    MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews(manager);

    }
    private void startCounting(GameManager manager) {
        flag = 1;
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       game(manager);
                    }
                });
            }
        }, 0, DELAY);
    }
    private void stopCounting() {
        carousalTimer.cancel();
    }


    private void initViews(GameManager manager) {
        main_BTN_war.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCounting(manager, new Timer());
                main_BTN_war.setEnabled(false);
            }
        });
    }


    private void findViews(){
        main_IMG_card1 = (ImageView)findViewById(R.id.main_IMG_card1);
        main_IMG_card2 = (ImageView)findViewById(R.id.main_IMG_card2);
        main_BTN_war = (Button)findViewById(R.id.main_BTN_war);
        main_LBL_score1 = (TextView)findViewById(R.id.main_LBL_score1);
        main_LBL_score2 = (TextView)findViewById(R.id.main_LBL_score2);
        main_LBL_progressBar = (ProgressBar) findViewById(R.id.main_LBL_progressBar);
    }



    private void increaseScore(GameManager manager, int player){
        if(player == p1){
            main_LBL_score1.setText(String.valueOf(manager.getPlayerScore1()));
        } else if(player == p2){
            main_LBL_score2.setText(String.valueOf(manager.getPlayerScore2()));
        }else{
            main_LBL_score1.setText(String.valueOf(manager.getPlayerScore1()));
            main_LBL_score2.setText(String.valueOf(manager.getPlayerScore2()));
        }
    }

    private void OneRoundOfGame(GameManager manager, int counter){
        int playerImage1 = getResources().getIdentifier(manager.getPlayerCards1().get(counter), "drawable", getPackageName());
        main_IMG_card2.setImageResource(playerImage1);

        int playerImage2 = getResources().getIdentifier(manager.getPlayerCards2().get(counter), "drawable", getPackageName());
        main_IMG_card1.setImageResource(playerImage2);

        //update score
        int player = manager.updateScore(counter);
        increaseScore(manager, player);

    }

    private void game(GameManager manager){
        playSound(R.raw.snd_tick);
        manager.createArray2();

        if(counter<26){
            OneRoundOfGame(manager, counter);


            if(counter==25){ //last round
                stopCounting();
                main_BTN_war.setText("END GAME");
                main_BTN_war.setEnabled(true);
            }

            counter++;
            main_LBL_progressBar.setProgress(counter);
        }
        else{ // 26 rounds end
            if(manager.getPlayerScore1() == manager.getPlayerScore2()){
                main_BTN_war.setText("Tie Breaker");
                r = new Random();
                int randNumber = r.nextInt(25) + 1;
                OneRoundOfGame(manager, randNumber);
            }
            else{ // game end
                stopCounting();
//                SharedPreferences prefs = getSharedPreferences("SP_FILE", MODE_PRIVATE);
//                String currentTTJson = prefs.getString("topTenJson", "");//"No name defined" is the default value.
//                TopTen currentTT = new Gson().fromJson(currentTTJson, TopTen.class);
//                String ttJson = new Gson().toJson(manager.manageTopTen(currentTT));
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("topTenJson", ttJson);
//                editor.apply();

                int winner = manager.checkWinner(manager.getPlayerScore1(), manager.getPlayerScore2());
                Intent myIntent = new Intent(Activity_Main.this, Activity_Winner.class);
                myIntent.putExtra(Activity_Winner.WINNER, winner);
                startActivity(myIntent);
                playSound(R.raw.snd_winner);
                finish();
            }
        }
    }

    private void playSound(int rawId) {
        mp = MediaPlayer.create(this, rawId);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
            }
        });
        mp.start();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(flag == 1){
            startCounting(manager);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopCounting();
    }
    private void setImage(int id, ImageView imageView) {
        Glide
                .with(this)
                .load(id)
                .into(imageView);
    }
}