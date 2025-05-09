package com.example.sportradar.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTest {

    @Test
    void createGame() {
        int startingScore = 0;
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        Team uruguay = new Team(uruguayName);
        Team brazil = new Team(brazilName);

        Game result = new Game(uruguay, brazil);

        assertEquals(uruguayName, result.getHomeTeam().getName());
        assertEquals(brazilName, result.getAwayTeam().getName());
        assertEquals(result.getScore().getHomeScore(), result.getScore().getAwayScore());
        assertEquals(startingScore, result.getScore().getHomeScore());
        assertNotNull(result.getUniqueGameId());
    }

    @Test
    void uuidGameDifference() {
        String uruguayName = "Uruguay";
        String brazilName = "Brazil";
        Team uruguay = new Team(uruguayName);
        Team brazil = new Team(brazilName);

        Game result1 = new Game(uruguay, brazil);
        Game result2 = new Game(uruguay, brazil);

        Assertions.assertNotEquals(result1.getUniqueGameId(), result2.getUniqueGameId());
    }
}
