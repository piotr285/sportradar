package com.example.sportradar.demo;

import java.util.List;
import java.util.UUID;

public interface Scoreboard {

    UUID startGame(TeamStat home, TeamStat away);
    Game finishGame(UUID gameId);
    void updateScore(Game game);
    List<Game> getSummaryByTotalScore();
}
