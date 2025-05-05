package com.example.sportradar.demo;

import java.util.List;

public class ScoreboardImpl implements Scoreboard {
    public List<Game> gameList; //wicej dodawania/usuwania czy przeglądania?

    public ScoreboardImpl(List<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public void startGame() {

    }

    @Override
    public void finishGame() {

    }

    @Override
    public void updateScore() {
        //receiving pair score
    }

    @Override
    public List<Game> getSummaryByTotalScore() {
        //Those games with the same total score
        //will be returned ordered by the most recently added to our system
        return gameList;
    }
}
