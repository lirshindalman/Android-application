package com.example.hw_1.activities;

import androidx.appcompat.app.AppCompatActivity;
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


    private FrameLayout main_LAY_list;
    private FrameLayout main_LAY_map;
    private TextView main_LBL_title;
    private Button main_BTN_updateList;

    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners_list);

        findViews();
//        initViews();

        fragment_list = new Fragment_List();
        fragment_list.setCallBack_top(callBack_top);
        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_list, fragment_list).commit();


        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.main_LAY_map, fragment_map).commit();
    }

    private void initViews() {

    }

    private void findViews() {
        main_LAY_list = findViewById(R.id.main_LAY_list);
        main_LAY_map = findViewById(R.id.main_LAY_map);
        main_LBL_title = findViewById(R.id.main_LBL_title);
    }


    private CallBack_Top callBack_top = new CallBack_Top() {
        @Override
        public void updateTitle(String str) {
            main_LBL_title.setText(str);
        }

        @Override
        public void addMarkerToMap(double lat, double lon) {
            fragment_map.addMarker(lat, lon);
        }
    };
}