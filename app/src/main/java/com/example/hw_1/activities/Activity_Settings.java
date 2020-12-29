package com.example.hw_1.activities;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hw_1.callbacks.CallBack_Top;
import com.example.hw_1.fragments.Fragment_List;


public class Activity_Settings extends AppCompatActivity implements CallBack_Top {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        Fragment_List fragment_list = new Fragment_List();
        fragment_list.setCallBack_top(this);
    }

    @Override
    public void addMarkerToMap(double lat, double lon) { }
}