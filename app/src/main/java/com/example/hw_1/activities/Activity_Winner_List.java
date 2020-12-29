package com.example.hw_1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.hw_1.R;
import com.example.hw_1.callbacks.CallBack_Top;
import com.example.hw_1.fragments.Fragment_List;
import com.example.hw_1.fragments.Fragment_Map;


public class Activity_Winner_List extends AppCompatActivity {


    private TextView main_LBL_title;
    private Button winner_list_BTN_start;
    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners_list);

        findViews();
        initViews();

        fragment_list = new Fragment_List();
        fragment_list.setCallBack_top(callBack_top);
        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_list, fragment_list).commit();


        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_map, fragment_map).commit();
    }

    private void initViews() {
        winner_list_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Activity_Winner_List.this, Activity_Main.class);
                startActivity(myIntent);
            }
        });

    }

    private void findViews() {
        main_LBL_title = findViewById(R.id.main_LBL_title);
        winner_list_BTN_start = (Button)findViewById(R.id.winner_list_BTN_start);
    }

    private CallBack_Top callBack_top = new CallBack_Top() {

        @Override
        public void addMarkerToMap(double lat, double lon) {
            fragment_map.addMarker(lat, lon);
        }
    };
}