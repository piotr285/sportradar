package com.example.sportradar.demo;

public class Score {

    private int homeScore;
    private int awayScore;

    public Score() {
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public int getSum() {
        return homeScore + awayScore;
    }
}
