package com.cinema.gateway.rest;

import com.cinema.gateway.rest.dto.Film;
import com.cinema.gateway.rest.dto.Hall;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final String url = "http://cinema-halls:8083/hall";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${hall.routing-key}")
    private String routingKey;

    @PostMapping
    public Hall createHall(@RequestBody Hall hall) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Hall> result =
                restTemplate.postForEntity(url, hall, Hall.class);
        return result.getBody();
    }

    @PostMapping("mq")
    public String createHallMq(@RequestBody Hall hall) {
        rabbitTemplate.convertAndSend(exchange, routingKey, hall);
        return "Sent successfully";
    }

    @GetMapping
    public List<Hall> getAllHalls() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Hall>> rateResponse =
                restTemplate.exchange("http://cinema-halls:8083/hall",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Hall>>() {
                        });
        List<Hall> hall = rateResponse.getBody();
        return hall;
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
