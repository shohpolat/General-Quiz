package com.example.generalquizapp.Models;

public class Category_U {

    private String name;
    private int _Id;
    private int easy_score=0;
    private int medium_score=0;
    private int hard_score=0;

    public Category_U(String name) {
        this.name = name;
    }

    public Category_U(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
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
