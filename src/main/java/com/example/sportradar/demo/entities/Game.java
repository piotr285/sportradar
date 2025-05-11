package com.example.sportradar.demo.entities;

import com.example.sportradar.demo.ScoreImpl;
import com.example.sportradar.demo.interfaces.Score;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Game {
    private static final AtomicLong idGenerator = new AtomicLong();
    private final UUID uniqueGameId;
    private final Team homeTeam;
    private final Team awayTeam;
    private final long sequenceNumber;
    private Score score;

    public Game(Team homeTeam, Team awayTeam) {
        this.uniqueGameId = UUID.randomUUID();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.sequenceNumber = idGenerator.incrementAndGet();
        this.score = new ScoreImpl();
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

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public Score getScore() {
        return score;
    }
}
