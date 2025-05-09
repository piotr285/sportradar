package com.example.sportradar.demo;

import com.example.sportradar.demo.exceptions.GameNotPresentException;
import com.example.sportradar.demo.exceptions.NegativeScoreException;

import java.util.List;
import java.util.UUID;

public interface Scoreboard {

    UUID startGame(Team home, Team away);
    Game finishGame(UUID gameId) throws GameNotPresentException;
    void updateScore(UUID gameId, int homeScore, int awayScore) throws GameNotPresentException, NegativeScoreException;
    List<Game> getSummaryByTotalScore();
}
