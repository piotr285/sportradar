package com.example.sportradar.demo.interfaces;

import com.example.sportradar.demo.exceptions.NegativeScoreException;

public interface Score {
    void updateScore(int homeScore, int awayScore) throws NegativeScoreException;
    int getSum();
    int getHomeScore();
    int getAwayScore();
}
