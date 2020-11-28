package com.example.gateway.rest;

import com.example.gateway.rest.dto.Seance;
import com.example.gateway.rest.dto.Ticket;
import com.example.gateway.rest.dto.TicketDto;
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

    private final String url = "http://cinema-seances:8082/seance";

    @PostMapping
    public Seance createSeance(@RequestBody Seance seance) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Seance> result =
                restTemplate.postForEntity(url, seance, Seance.class);
        return result.getBody();
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
