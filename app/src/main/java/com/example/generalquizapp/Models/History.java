package com.example.generalquizapp.Models;

public class History {

    private String name;
    private String difficulty;
    private String score;
    private String time;
    private String language;

    public History(String name, String difficulty, String score, String time,String language) {
        this.name = name;
        this.difficulty = difficulty;
        this.score = score;
        this.time = time;
        this.language = language;
    }

    public History() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
