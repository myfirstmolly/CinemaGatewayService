package com.example.gateway.api;

import com.example.gateway.dto.Worker;
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
@RequestMapping("/worker")
@NoArgsConstructor
public class WorkerController {

    private final String url = "http://cinema-workers:8085/worker";

    @PostMapping
    public Worker createWorker(@RequestBody Worker worker) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Worker> result =
                restTemplate.postForEntity(url, worker, Worker.class);
        return result.getBody();
    }

    @GetMapping
    public List<Worker> getAllWorkers() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Worker>> result =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

    @GetMapping("{id}")
    public Worker getById(@PathVariable(value = "id") UUID id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Worker> result =
                restTemplate.exchange(url + "/" + id.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return result.getBody();
    }

}
