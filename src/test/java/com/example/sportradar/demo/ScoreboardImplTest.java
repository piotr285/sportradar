package com.example.sportradar.demo;

import com.example.sportradar.demo.entities.Game;
import com.example.sportradar.demo.entities.Team;
import com.example.sportradar.demo.exceptions.GameNotPresentException;
import com.example.sportradar.demo.exceptions.NegativeScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardImplTest {
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

        assertEquals(1, currentList.size());
        assertEquals(brazilName, currentList.get(0).getAwayTeam().getName());
        assertEquals(startedGameId, currentList.get(0).getUniqueGameId());
        assertEquals(currentList.get(0).getScore().getHomeScore(), currentList.get(0).getScore().getAwayScore());
        assertEquals(startingScore, currentList.get(0).getScore().getHomeScore());
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

        assertTrue(currentList.isEmpty());
    }

    @Test
    public void finishGameTest_throwException() {
        ScoreboardImpl scoreboard = new ScoreboardImpl(new ArrayList<>());
        assertThrows(GameNotPresentException.class, () ->
                scoreboard.finishGame(UUID.randomUUID()));
    }

    @Test
    public void updateGameTest() throws GameNotPresentException, NegativeScoreException {
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

        assertEquals(1, currentList.size());
        assertEquals(brazilName, currentList.get(0).getAwayTeam().getName());
        assertEquals(startedGameId, currentList.get(0).getUniqueGameId());
        assertEquals(homeScore, currentList.get(0).getScore().getHomeScore());
        assertEquals(awayScore, currentList.get(0).getScore().getAwayScore());
    }

    @Test
    public void updateGameTest_throwGameException() {
        ScoreboardImpl scoreboard = new ScoreboardImpl(new ArrayList<>());
        assertThrows(GameNotPresentException.class, () ->
                scoreboard.updateScore(UUID.randomUUID(), 1, 1));
    }

    @ParameterizedTest
    @MethodSource("provideGameScenarios")
    @DisplayName("Test getSummaryByTotalScore with multiple game scenarios")
    void getSummaryByTotalScore_MultipleScenarios(List<GameSetup> setups, List<Integer> expectedOrder) throws GameNotPresentException, NegativeScoreException {
        ScoreboardImpl scoreboard = new ScoreboardImpl(new ArrayList<>());
        List<UUID> gameIds = new ArrayList<>();
        for (GameSetup setup : setups) {
            UUID gameId = scoreboard.startGame(new Team(setup.homeTeam), new Team(setup.awayTeam));
            scoreboard.updateScore(gameId, setup.homeScore, setup.awayScore);
            gameIds.add(gameId);
        }
        List<Game> result = scoreboard.getSummaryByTotalScore();

        for (int i = 0; i < expectedOrder.size(); i++) {
            int expectedIndex = expectedOrder.get(i);
            assertEquals(gameIds.get(expectedIndex), result.get(i).getUniqueGameId(),
                    "Game at position " + i + " does not match");
        }
    }

    private static Stream<Arguments> provideGameScenarios() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new GameSetup("Uruguay", "Italy", 0, 5),
                                new GameSetup("Spain", "Brazil", 2, 2),
                                new GameSetup("Mexico", "Canada", 6, 6),
                                new GameSetup("Argentina", "Australia", 3, 1),
                                new GameSetup("Germany", "France", 10, 2)
                        ),
                        List.of(4, 2, 0, 3, 1)
                ),
                Arguments.of(
                        List.of(
                                new GameSetup("A", "B", 1, 1),
                                new GameSetup("C", "D", 1, 1),
                                new GameSetup("E", "F", 2, 0),
                                new GameSetup("G", "H", 0, 2),
                                new GameSetup("I", "J", 2, 0)
                        ),
                        List.of(4,3,2,1,0)
                ),
                Arguments.of(
                        List.of(
                                new GameSetup("A", "B", 5, 0),
                                new GameSetup("C", "D", 3, 1),
                                new GameSetup("E", "F", 2, 1),
                                new GameSetup("G", "H", 2, 0),
                                new GameSetup("I", "J", 1, 0)
                        ),
                        List.of(0,1,2,3,4)
                ),
                Arguments.of(
                        List.of(
                                new GameSetup("A", "B", 1, 0),
                                new GameSetup("C", "D", 2, 0),
                                new GameSetup("E", "F", 3, 1),
                                new GameSetup("G", "H", 2, 1),
                                new GameSetup("I", "J", 5, 0)
                        ),
                        List.of(4,2,3,1,0)
                )
        );
    }

    private record GameSetup(String homeTeam, String awayTeam, int homeScore, int awayScore) {}
}
