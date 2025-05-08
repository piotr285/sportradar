package com.example.sportradar.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void createGame() {
        int startingScore =0;
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        Team uruguay = new Team(uruguayName);
        Team brazil = new Team(brazilName);

        Game result = new Game(uruguay, brazil);

        Assertions.assertEquals(uruguayName, result.getHomeTeam().getName());
        Assertions.assertEquals(brazilName, result.getAwayTeam().getName());
        Assertions.assertEquals(result.getScore().getHomeScore(), result.getScore().getAwayScore());
        Assertions.assertEquals(startingScore, result.getScore().getHomeScore());
        Assertions.assertNotNull(result.getUniqueGameId());
    }

    @Test
    public void uuidGameDifference() {
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        Team uruguay = new Team(uruguayName);
        Team brazil = new Team(brazilName);

        Game result1 = new Game(uruguay, brazil);
        Game result2 = new Game(uruguay, brazil);

        Assertions.assertNotEquals(result1.getUniqueGameId(), result2.getUniqueGameId());
    }
}
