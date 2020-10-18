package com.example.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Seance {
    
    private UUID seanceId;
    private String seanceDate;
    private double price;
    private UUID filmID;
    private UUID hallID;

    public Seance(String seanceDate, double price, UUID filmID, UUID hallID) {
        seanceId = UUID.randomUUID();
        this.seanceDate = seanceDate;
        this.price = price;
        this.filmID = filmID;
        this.hallID = hallID;
    }
    
}
