package com.example.sportradar.demo;

import com.example.sportradar.demo.exceptions.NegativeScoreException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreTest {

    @Test
    void updateScore() throws NegativeScoreException {
        ScoreImpl score = new ScoreImpl();
        int homeScore = 1;
        int awayScore = 2;
        score.updateScore(homeScore, awayScore);
        assertEquals(homeScore, score.getHomeScore());
        assertEquals(awayScore, score.getAwayScore());
    }

    @Test
    void updateScore_NegativeScoreException_homeScore() {
        ScoreImpl score = new ScoreImpl();
        int homeScore = -1;
        int awayScore = 2;
        assertThrows(NegativeScoreException.class, () ->
                score.updateScore(homeScore, awayScore));
    }

    @Test
    void updateScore_NegativeScoreException_awayScore() {
        ScoreImpl score = new ScoreImpl();
        int homeScore = 1;
        int awayScore = -2;
        assertThrows(NegativeScoreException.class, () ->
                score.updateScore(homeScore, awayScore));
    }
}
