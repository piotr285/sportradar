package com.example.sportradar.demo;

import com.example.sportradar.demo.exceptions.NegativeScoreException;
import com.example.sportradar.demo.interfaces.Score;

public class ScoreImpl implements Score {

    private int homeScore;
    private int awayScore;

    public ScoreImpl() {
        this.homeScore = 0;
        this.awayScore = 0;
    }

    @Override
    public void updateScore(int homeScore, int awayScore) throws NegativeScoreException {
        if (homeScore<0) {
            throw new NegativeScoreException("Home score cannot be lower than zero");
        } else if (awayScore <0) {
            throw new NegativeScoreException("Away score cannot be lower than zero");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public int getHomeScore() {
        return homeScore;
    }

    @Override
    public int getAwayScore() {
        return awayScore;
    }

    @Override
    public int getSum() {
        return homeScore + awayScore;
    }
}
