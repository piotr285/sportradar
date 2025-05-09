package com.example.sportradar.demo;

import com.example.sportradar.demo.exceptions.NegativeScoreException;

public class Score {

    private int homeScore;
    private int awayScore;

    public Score() {
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public void updateScore(int homeScore, int awayScore) throws NegativeScoreException {
        if (homeScore<0) {
            throw new NegativeScoreException("Home score cannot be lower than zero");
        } else if (awayScore <0) {
            throw new NegativeScoreException("Away score cannot be lower than zero");
        }
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
