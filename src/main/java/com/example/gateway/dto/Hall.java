package com.example.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Hall {
    
    private UUID hallId;
    private String name;
    private int linesNum;
    private int seatsNum;

    public Hall(String name, int linesNum, int seatsNum) {
        hallId = UUID.randomUUID();
        this.name = name;
        this.linesNum = linesNum;
        this.seatsNum = seatsNum;
    }
}
