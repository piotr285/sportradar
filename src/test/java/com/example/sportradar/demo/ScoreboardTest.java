package com.example.sportradar.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScoreboardTest {

    @Test
    public void startGameTest() {
        int startingScore =0;
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        TeamStat uruguay = new TeamStat(uruguayName);
        TeamStat brazil = new TeamStat(brazilName);
        ScoreboardImpl scoreboard = new ScoreboardImpl( new ArrayList<>());

        UUID startedGameId = scoreboard.startGame(uruguay, brazil);
        List<Game> currentList = scoreboard.getSummaryByTotalScore();

        Assertions.assertEquals(1, currentList.size());
        Assertions.assertEquals(brazilName, currentList.get(0).getAwayTeam().getName());
        Assertions.assertEquals(startedGameId, currentList.get(0).getUniqueGameId());
        Assertions.assertEquals(currentList.get(0).getHomeTeam().getScore(), currentList.get(0).getAwayTeam().getScore());
        Assertions.assertEquals(startingScore, currentList.get(0).getHomeTeam().getScore());
    }

    @Test
    public void finishGameTest() {
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        TeamStat uruguay = new TeamStat(uruguayName);
        TeamStat brazil = new TeamStat(brazilName);
        ScoreboardImpl scoreboard = new ScoreboardImpl( new ArrayList<>());

        UUID startedGameId = scoreboard.startGame(uruguay, brazil);
        List<Game> currentList = scoreboard.getSummaryByTotalScore();
        scoreboard.finishGame(startedGameId);

        Assertions.assertTrue(currentList.isEmpty());
    }

//    @Test
//    public void getSummaryTest() {
//        ScoreboardImpl scoreboard = new ScoreboardImpl( new ArrayList<>());
//        scoreboard.startGame("Uruguay", "Italy"));
//        scoreboard.startGame("Spain", "Brazil"));
//        scoreboard.startGame("Mexico", "Canada"));
//        scoreboard.startGame("Argentina", "Australia"));
//        scoreboard.startGame("Germany", "France"));
//        //todo : dokończyć po implementacji reszty metod
//    }

    private Game prepareGame(String homeTeam, String awayTeam) {
        TeamStat home = new TeamStat(homeTeam);
        TeamStat away = new TeamStat(awayTeam);
        return new Game(home, away);
    }
}
