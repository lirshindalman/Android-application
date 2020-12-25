package com.example.hw_1.objects;

import android.content.Context;

public class Record implements Comparable<Record>{

    private String name = "";
    private long map = 0;
    private int score = 0;

    public Record() { }

    public Record(String name, long map, int score) {
        this.name = name;
        this.map = map;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public long getMap() {
        return map;
    }

    public Record setMap(long date) {
        this.map = date;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }

    @Override
    public int compareTo(Record u) {
        if (this.getScore() ==  u.getScore()) {
            return 1;
        }
        return this.getScore()>(u.getScore())? -1 : 1;
    }
}