package com.example.beckyrutherford_comp228lab5;

import javafx.beans.property.SimpleStringProperty;

public class PlayerGameRecord {
    public SimpleStringProperty date;
    public SimpleStringProperty game;
    public SimpleStringProperty score;

    //Constructor
    public PlayerGameRecord(String date, String game, String score){
        this.date = new SimpleStringProperty(date);
        this.game = new SimpleStringProperty(game);
        this.score = new SimpleStringProperty(score);

    }
    //getters
    public String getDate(){
        return date.get();
    }

    public String getGame() {
        return game.get();
    }
    public String getScore(){
        return score.get();
    }
}
