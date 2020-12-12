package com.cinema.gateway.rest;

import com.cinema.gateway.rest.dto.Hall;
import com.cinema.gateway.rest.dto.Seance;
import com.cinema.gateway.rest.dto.Ticket;
import com.cinema.gateway.rest.dto.TicketDto;
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
@RequestMapping("/seance")
@NoArgsConstructor
public class SeanceController {

    private final String url = "http://cinema-seances:8082/seance";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${seance.routing-key}")
    private String routingKey;

    @PostMapping
    public Seance createSeance(@RequestBody Seance seance) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Seance> result =
                restTemplate.postForEntity(url, seance, Seance.class);
        return result.getBody();
    }

    @PostMapping("mq")
    public String createSeanceMq(@RequestBody Seance seance) {
        rabbitTemplate.convertAndSend(exchange, routingKey, seance);
        return "Sent successfully";
    }

    @PostMapping("{seanceId}/visitor")
    public TicketDto buyTicket(@PathVariable(value = "seanceId") UUID id,
                               @RequestBody(required = false) Ticket ticketRequest) {
        TicketDto ticketDto = new TicketDto(ticketRequest.getVisitor().getUserId(),
                ticketRequest.getLine(), ticketRequest.getPlace());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TicketDto> result =
                restTemplate.postForEntity(url + "/" + id.toString() + "/visitor",
                        ticketDto, TicketDto.class);
        return result.getBody();
    }

    @GetMapping
    public List<Seance> getAllSeances() {
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
