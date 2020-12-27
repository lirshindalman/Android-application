package com.example.hw_1.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.hw_1.R;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;

import com.example.hw_1.callbacks.CallBack_Top;
import com.example.hw_1.objects.TopTen;
import com.google.gson.Gson;

import java.util.ArrayList;


public class Fragment_List extends Fragment {


    ListView listview;

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



        
        SharedPreferences prefs = this.getActivity().getSharedPreferences("SP_FILE_TOP_TEN", Context.MODE_PRIVATE);
        String currentTTJson = prefs.getString("topTenJson", "");//"No name defined" is the default value.
        //convert from json to TopTen
        TopTen currentTT = new Gson().fromJson(currentTTJson, TopTen.class);
        final ArrayList<String> arrayList=setArry(currentTT);

//Create Adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        

//assign adapter to listview
        listview.setAdapter(arrayAdapter);
        return view;
    }

    private ArrayList<String> setArry(TopTen topTen) {
        final ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < topTen.getRecords().size(); i++) {
            String str = i+1 +" - "+topTen.getRecords().get(i).toString();
            arrayList.add(str);
        }
        return arrayList;
    }

    private void initViews() {
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (callBack_top != null) {
                callBack_top.addMarkerToMap(32.07158054366349, 34.81063892756903);
            }
        }
    };


    private void findViews(View view) {
        listview = (ListView) view.findViewById(R.id.listview);
    }
}