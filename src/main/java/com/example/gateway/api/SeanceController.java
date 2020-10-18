package com.example.gateway.api;

import com.example.gateway.dto.Seance;
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
@RequestMapping("/seance")
@NoArgsConstructor
public class SeanceController {

    private final String url = "http://10.108.133.183:8082/seance";

    @PostMapping
    public Seance createSeance(@RequestBody Seance seance) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Seance> result =
                restTemplate.postForEntity(url, seance, Seance.class);
        return result.getBody();
    }

    @GetMapping
    public List<Seance> getAllFilms() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Seance>> result =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @GetMapping("{id}")
    public Seance getById(@PathVariable(value = "id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Seance> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }
    
}
