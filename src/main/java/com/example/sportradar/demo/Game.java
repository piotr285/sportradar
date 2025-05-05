package com.example.sportradar.demo;

import java.util.UUID;

public class Game {

    private UUID uniqueGameId;
    private TeamStat homeTeam;
    private TeamStat awayTeam;

    public Game(TeamStat homeTeam, TeamStat awayTeam) {
        this.uniqueGameId = UUID.randomUUID();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public UUID getUniqueGameId() {
        return uniqueGameId;
    }

    public TeamStat getHomeTeam() {
        return homeTeam;
    }

    public TeamStat getAwayTeam() {
        return awayTeam;
    }
}
