package com.example.generalquizapp.Models;

public class Category_E {

    private int _ID;
    private String name;
    private int easy_score=0;
    private int medium_score=0;
    private int hard_score=0;

    public Category_E(String name) {
        this.name = name;
    }

    public Category_E(){}

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEasy_score() {
        return easy_score;
    }

    public void setEasy_score(int easy_score) {
        this.easy_score = easy_score;
    }

    public int getMedium_score() {
        return medium_score;
    }

    public void setMedium_score(int medium_score) {
        this.medium_score = medium_score;
    }

    public int getHard_score() {
        return hard_score;
    }

    public void setHard_score(int hard_score) {
        this.hard_score = hard_score;
    }
}
