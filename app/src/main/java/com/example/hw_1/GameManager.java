package com.example.hw_1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {
    public static final int p1 = 1;
    public static final int p2 = 2;
    private ArrayList<String> allCards = new ArrayList<>();
    private List<String> PlayerCards1;
    private List<String> PlayerCards2;
    private int playerScore1;
    private int playerScore2;

    public GameManager() {
        this.playerScore1 = 0;
        this.playerScore2 = 0;
    }

    public void createArray2(){
        for(int i=2; i<15; i++){
            allCards.add("card_" + "a" + i);
        }
        for(int i=2; i<15; i++){
            allCards.add("card_" + "b" + i);
        }
        for(int i=2; i<15; i++){
            allCards.add("card_" + "c" + i);
        }
        for(int i=2; i<15; i++){
            allCards.add("card_" + "d" + i);
        }
        Collections.shuffle(allCards);
        PlayerCards1 = allCards.subList(0,26);
        PlayerCards2 = allCards.subList(26,52);
    }

    public int checkWinner(int playerScore1 , int playerScore2){
        return playerScore1 > playerScore2 ? p1 : p2;
    }

    public int updateScore(int counter){
        String s1 = getPlayerCards1().get(counter).substring(6);
        int playerCard1 = Integer.parseInt(s1);

        String s2 = getPlayerCards2().get(counter).substring(6);
        int playerCard2 = Integer.parseInt(s2);

        return increaseScore(playerCard1, playerCard2);
    }



    public int increaseScore(int playerCard1, int playerCard2){
        if(playerCard1 > playerCard2){
            playerScore1++;
            return p1;
        } else if(playerCard2 > playerCard1){
            playerScore2++;

            return p2;
        }else{
            playerScore1++;
            playerScore2++;
            return 0;
        }
    }

    public  int getPlayerScore1() {
        return playerScore1;
    }

    public int getPlayerScore2() {
        return playerScore2;
    }

    public List<String> getPlayerCards1() {
        return PlayerCards1;
    }

    public List<String> getPlayerCards2() {
        return PlayerCards2;
    }



}
