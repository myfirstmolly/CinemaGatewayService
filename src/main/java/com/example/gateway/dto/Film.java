package com.example.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Film {
    
    private UUID filmId;
    private String name;
    private String director;
    private Integer year;
    private String genre;

    public Film(String name, String director, Integer year, String genre) {
        this.filmId = UUID.randomUUID();
        this.name = name;
        this.director = director;
        this.year = year;
        this.genre = genre;
    }
    
}
