package com.example.sportradar.demo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ScoreboardImpl implements Scoreboard {
    private List<Game> gameList;

    public ScoreboardImpl(List<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public UUID startGame(Team home, Team away) {
        Game newGame = new Game(home, away);
        gameList.add(newGame);
        return newGame.getUniqueGameId();
    }

    @Override
    public Game finishGame(UUID gameId) throws GameNotPresentException { //todo ogarnąć wyjątki
        Game game = findGameById(gameId);
        gameList.remove(game);
        return game;
    }

    @Override
    public void updateScore(UUID gameId, int homeScore, int awayScore) throws GameNotPresentException {
        Game game = findGameById(gameId);
        game.getScore().updateScore(homeScore, awayScore);
    }

    @Override
    public List<Game> getSummaryByTotalScore() {
        return gameList.stream()
                .sorted(Comparator.comparing(Game::getScore, Comparator.comparingInt(Score::getSum))
                        .thenComparing(Game:: getTimeAdded).reversed())
                .toList();
    }

    private Game findGameById(UUID gameId) throws GameNotPresentException {
        Optional<Game> gameExists = gameList.stream()
                .filter(game -> game.getUniqueGameId().equals(gameId))
                .findAny();
        if (gameExists.isPresent()) {
            return gameExists.get();
        } else throw new GameNotPresentException();
    }
}
