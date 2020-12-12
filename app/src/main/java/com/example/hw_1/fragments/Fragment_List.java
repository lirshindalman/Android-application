package com.example.hw_1.fragments;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.hw_1.R;

import androidx.fragment.app.Fragment;

import com.example.hw_1.callbacks.CallBack_Top;


public class Fragment_List extends Fragment {

    private TextView list_LBL_title;
    private Button list_BTN_changeActivity;

    private CallBack_Top callBack_top;

    public void setCallBack_top(CallBack_Top _callBack_top) {
        this.callBack_top = _callBack_top;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        initViews();

        refreshList();
        return view;
    }

    private void initViews() {
        list_BTN_changeActivity.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (callBack_top != null) {
                callBack_top.addMarkerToMap(32.07158054366349, 34.81063892756903);
            }
        }
    };

    public void refreshList() {
        String date = DateFormat.format("dd.MM.yy\nHH:mm:ss", System.currentTimeMillis()).toString();
        list_LBL_title.setText(date);
    }

    private void findViews(View view) {
        list_LBL_title = view.findViewById(R.id.list_LBL_title);
        list_BTN_changeActivity = view.findViewById(R.id.list_BTN_changeActivity);
    }
}