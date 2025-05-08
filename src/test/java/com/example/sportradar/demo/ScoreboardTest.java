package com.example.sportradar.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScoreboardTest {

    @Test
    public void startGameTest() {
        int startingScore = 0;
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        Team uruguay = new Team(uruguayName);
        Team brazil = new Team(brazilName);
        ScoreboardImpl scoreboard = new ScoreboardImpl(new ArrayList<>());

        UUID startedGameId = scoreboard.startGame(uruguay, brazil);
        List<Game> currentList = scoreboard.getSummaryByTotalScore();

        Assertions.assertEquals(1, currentList.size());
        Assertions.assertEquals(brazilName, currentList.get(0).getAwayTeam().getName());
        Assertions.assertEquals(startedGameId, currentList.get(0).getUniqueGameId());
        Assertions.assertEquals(currentList.get(0).getScore().getHomeScore(), currentList.get(0).getScore().getAwayScore());
        Assertions.assertEquals(startingScore, currentList.get(0).getScore().getHomeScore());
    }

    @Test
    public void finishGameTest() throws GameNotPresentException {
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        Team uruguay = new Team(uruguayName);
        Team brazil = new Team(brazilName);
        ScoreboardImpl scoreboard = new ScoreboardImpl(new ArrayList<>());

        UUID startedGameId = scoreboard.startGame(uruguay, brazil);
        scoreboard.finishGame(startedGameId);
        List<Game> currentList = scoreboard.getSummaryByTotalScore();

        Assertions.assertTrue(currentList.isEmpty());
    }

    @Test
    public void updateGameTest() throws GameNotPresentException {
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        Team uruguay = new Team(uruguayName);
        Team brazil = new Team(brazilName);
        ScoreboardImpl scoreboard = new ScoreboardImpl(new ArrayList<>());

        UUID startedGameId = scoreboard.startGame(uruguay, brazil);
        List<Game> currentList = scoreboard.getSummaryByTotalScore();
        int homeScore = 4;
        int awayScore = 3;
        scoreboard.updateScore(startedGameId, homeScore, awayScore);

        Assertions.assertEquals(1, currentList.size());
        Assertions.assertEquals(brazilName, currentList.get(0).getAwayTeam().getName());
        Assertions.assertEquals(startedGameId, currentList.get(0).getUniqueGameId());
        Assertions.assertEquals(homeScore, currentList.get(0).getScore().getHomeScore());
        Assertions.assertEquals(awayScore, currentList.get(0).getScore().getAwayScore());
    }

    @Test
    public void getSummaryTest() throws GameNotPresentException {
        ScoreboardImpl scoreboard = new ScoreboardImpl(new ArrayList<>());
        UUID game1 = scoreboard.startGame(new Team("Uruguay"), new Team("Italy"));
        UUID game2 = scoreboard.startGame(new Team("Spain"), new Team("Brazil"));
        UUID game3 = scoreboard.startGame(new Team("Mexico"), new Team("Canada"));
        UUID game4 = scoreboard.startGame(new Team("Argentina"), new Team("Australia"));
        UUID game5 = scoreboard.startGame(new Team("Germany"), new Team("France"));

        scoreboard.updateScore(game1, 0, 5);
        scoreboard.updateScore(game2, 2, 2);
        scoreboard.updateScore(game3, 6, 6);
        scoreboard.updateScore(game4, 3, 1);
        scoreboard.updateScore(game5, 10, 2);

        List<Game> result = scoreboard.getSummaryByTotalScore();

        Assertions.assertEquals(game5, result.get(0).getUniqueGameId());
        Assertions.assertEquals(game3, result.get(1).getUniqueGameId());
        Assertions.assertEquals(game1, result.get(2).getUniqueGameId());
        Assertions.assertEquals(game4, result.get(3).getUniqueGameId());
        Assertions.assertEquals(game2, result.get(4).getUniqueGameId());
    }

}
