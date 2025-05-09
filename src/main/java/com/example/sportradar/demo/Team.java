package com.example.sportradar.demo;

public record Team (String name){
    public String getName() {
        return name();
    }
}
