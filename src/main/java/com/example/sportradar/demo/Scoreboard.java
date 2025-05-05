package com.example.sportradar.demo;

import java.util.List;

public interface Scoreboard {

    void startGame();
    void finishGame();
    void updateScore();
    List<Game> getSummaryByTotalScore();
}
