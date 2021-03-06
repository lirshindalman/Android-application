package com.example.hw_1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hw_1.R;
import com.example.hw_1.objects.TopTen;
import com.google.gson.Gson;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.core.content.ContextCompat.getSystemService;
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
    final int DEFAULT = 0;
    GameManager manager = new GameManager();
    MediaPlayer mp;
    private  int flag = DEFAULT;
    LocationManager locationManager;
    private double lon ;
    private double lat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews(manager);
        setImage(R.drawable.background, main_IMG_background );
        setImage(R.drawable.back_card, main_IMG_card1);
        setImage(R.drawable.back_card, main_IMG_card2);
        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
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
        carousalTimer = new Timer();
        main_BTN_war.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCounting(manager);
                main_BTN_war.setEnabled(false);
            }
        });
    }


    private void findViews(){
        main_IMG_background = (ImageView)findViewById(R.id.main_IMG_background);
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
        setImage(playerImage1, main_IMG_card1);

        int playerImage2 = getResources().getIdentifier(manager.getPlayerCards2().get(counter), "drawable", getPackageName());
        setImage(playerImage2, main_IMG_card2);

        //update score
        int player = manager.updateScore(counter);
        increaseScore(manager, player);

    }

    @SuppressLint("MissingPermission")
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
                LocationListener locationListener = location -> {
                    lon = location.getLongitude();
                    lat = location.getLatitude();
//                    Log.d("d:onLocationChanged", "lon: "+lon);
//                    Log.d("d:onLocationChanged", "lat: "+lat);
                };

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location!= null) {
                    lon = location.getLongitude();
                    lat = location.getLatitude();
                }
//                Log.d("d:locationManager", "lon: "+lon);
//                Log.d("d:locationManager", "lat: "+lat);

                //pull winner top ten from Shared Preferences
                SharedPreferences prefs = getSharedPreferences("LIR_AVIV", MODE_PRIVATE);
                String currentTTJson = prefs.getString("topTenJson", "");//"No name defined" is the default value.
                //convert from json to TopTen
                TopTen currentTT = new Gson().fromJson(currentTTJson, TopTen.class);
                //convert from TopTen to json
                String ttJson = new Gson().toJson(manager.manageTopTen(currentTT,lon,lat));
                Log.d("d: ", "json: "+ttJson);
                //set new winner top ten from Shared Preferences
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("topTenJson", ttJson);
                editor.apply();

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