package com.example.gateway.api;

import com.example.gateway.dto.Hall;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hall")
@NoArgsConstructor
public class HallController {

    private final String url = "http://10.99.144.4:8083/hall";

    @PostMapping
    public Hall createHall(@RequestBody Hall hall) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Hall> result =
                restTemplate.postForEntity(url, hall, Hall.class);
        return result.getBody();
    }

    @GetMapping
    public List<Hall> getAllSeances() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Hall>> result =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @GetMapping("{id}")
    public Hall getById(@PathVariable(value = "id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Hall> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }
    
}
