package com.example.sportradar.demo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ScoreboardImpl implements Scoreboard {
    private List<Game> gameList; //wicej dodawania/usuwania czy przeglądania?

    public ScoreboardImpl(List<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public UUID startGame(TeamStat home, TeamStat away) {
        Game newGame = new Game(home, away);
        gameList.add(newGame);
        return newGame.getUniqueGameId();
    }

    @Override
    public Game finishGame(UUID gameId) {
        Optional<Game> gameExists = gameList.stream()
                .filter(game -> game.getUniqueGameId().equals(gameId))
                .findAny();
        if (gameExists.isPresent()) {
            gameList.remove(gameExists.get());
            return gameExists.get();
        }
        //todo jakiś wyjątek??
        return null;
    }

    @Override
    public void updateScore(Game game) {
        //receiving pair score
    }

    @Override
    public List<Game> getSummaryByTotalScore() {
        //Those games with the same total score
        //will be returned ordered by the most recently added to our system
        return gameList;
    }
}
