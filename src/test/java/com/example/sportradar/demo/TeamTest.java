package com.example.sportradar.demo;

import com.example.sportradar.demo.entities.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TeamTest {
    @Test
    void createTeam_nullName() {
        String uruguayName = null;
        assertThrows(IllegalArgumentException.class, () ->
                new Team(uruguayName));
    }
}
