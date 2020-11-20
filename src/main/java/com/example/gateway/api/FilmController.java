package com.example.gateway.api;

import com.example.gateway.dto.Film;
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
@RequestMapping("/film")
@NoArgsConstructor
public class FilmController {

    private final String url = "http://cinema-films:8081/film";

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Film> result =
                restTemplate.postForEntity(url, film, Film.class);
        return result.getBody();
    }

    @GetMapping
    public List<Film> getAllFilms() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Film>> result =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @GetMapping("{id}")
    public Film getById(@PathVariable(value = "id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Film> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

}
