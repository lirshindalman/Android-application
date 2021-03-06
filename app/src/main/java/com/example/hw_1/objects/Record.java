package com.example.hw_1.objects;

public class Record implements Comparable<Record>{

    private String name = "";
    private double mapLon = 0;
    private double mapLat = 0;
    private int score = 0;


    public Record() { }

    public Record(String name, double mapLon, double mapLat, int score) {
        this.name = name;
        this.mapLon = mapLon;
        this.mapLat = mapLat;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public double getMapLon() {
        return mapLon;
    }
    public double getMapLat() {
        return mapLat;
    }

    public Record setMap(long mapLon,long mapLat ) {
        this.mapLon = mapLon;
        this.mapLat = mapLat;
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

    public String toString(){
        String str = this.getName() + "    score: " + this.getScore(); // +" lon: "+ this.getMapLon() + " lat: "+this.getMapLat();
        return str;
    }
}