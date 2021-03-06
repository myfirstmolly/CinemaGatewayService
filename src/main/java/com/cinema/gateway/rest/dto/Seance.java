package com.cinema.gateway.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Seance {
    
    private UUID seanceId;
    private String seanceDate;
    private double price;
    private UUID hallId;
    private UUID filmId;

}
