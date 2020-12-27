package com.example.hw_1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hw_1.R;

import androidx.fragment.app.Fragment;

import com.example.hw_1.callbacks.CallBack_Bottom;


public class Fragment_Map extends Fragment {

    private TextView map_LBL_location;

    private CallBack_Bottom callBack_bottom;

    public void setCallBack_bottom(CallBack_Bottom callBack_bottom) {
        this.callBack_bottom = callBack_bottom;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        findViews(view);
        initViews();

        return view;
    }

    public void addMarker(double lat, double lon) {
        map_LBL_location.setText(lat + "\n" + lon);
    }

    private void initViews() {

    }

    private void findViews(View view) {
        map_LBL_location = view.findViewById(R.id.map_LBL_location);
    }
}