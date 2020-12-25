package com.example.hw_1.objects;

import java.util.ArrayList;

public class TopTen {

    private int maxRecord = 0;
    private int minRecord = 0;
    private ArrayList<Record> records = new ArrayList<>();

    public TopTen() { }

    public TopTen(ArrayList<Record> records) {
        this.records = records;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public TopTen setRecords(ArrayList<Record> records) {
        this.records = records;
        return this;
    }

    public int getMaxRecord() {
        return maxRecord;
    }

    public int getMinRecord() {
        return minRecord;
    }

    public void setMaxRecord(int max) {
        this.maxRecord = max;
    }

    public void setMinRecord(int min) {
        this.minRecord= min;
    }
}