package com.example.sportradar.demo.entities;

public record Team (String name){
    public Team {
        if (name==null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    public String getName() {
        return name();
    }
}
