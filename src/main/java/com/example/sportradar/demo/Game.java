package com.example.sportradar.demo;

import java.time.Instant;
import java.util.UUID;

public class Game {

    private final UUID uniqueGameId;
    private final Team homeTeam;
    private final Team awayTeam;
    private final Instant timeAdded;
    private Score score;

    public Game(Team homeTeam, Team awayTeam) {
        this.uniqueGameId = UUID.randomUUID();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.timeAdded = Instant.now();
        this.score = new Score();
    }

    public UUID getUniqueGameId() {
        return uniqueGameId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Instant getTimeAdded() {
        return timeAdded;
    }

    public Score getScore() {
        return score;
    }
}
