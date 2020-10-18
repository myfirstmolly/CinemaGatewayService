package com.example.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Visitor {
    
    private UUID userId;
    private String name;
    private double money;
    private int age;

    public Visitor(String name, double money, int age) {
        userId = UUID.randomUUID();
        this.name = name;
        this.money = money;
        this.age = age;
    }
    
}
