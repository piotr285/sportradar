package com.example.sportradar.demo;

public class TeamStat {

    private String name;
    private int score;

    public TeamStat(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
