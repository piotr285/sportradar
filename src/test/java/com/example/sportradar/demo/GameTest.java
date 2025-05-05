package com.example.sportradar.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void createGame() {
        int startingScore =0;
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        TeamStat uruguay = new TeamStat(uruguayName);
        TeamStat brazil = new TeamStat(brazilName);

        Game result = new Game(uruguay, brazil);

        Assertions.assertEquals(uruguayName, result.getHomeTeam().getName());
        Assertions.assertEquals(brazilName, result.getAwayTeam().getName());
        Assertions.assertEquals(result.getHomeTeam().getScore(), result.getAwayTeam().getScore());
        Assertions.assertEquals(startingScore, result.getHomeTeam().getScore());
    }
}
