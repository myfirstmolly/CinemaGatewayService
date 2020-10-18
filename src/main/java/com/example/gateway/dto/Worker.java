package com.example.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Worker {
    
    private UUID workerId;
    private String name;
    private String surname;
    private int salary;
    private Position position;

    Worker(String name, String surname, int salary, Position position) {
        this.workerId = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.position = position;
    }
    
}
