package com.example.erddemo.controller;

import com.example.erddemo.model.Reservation;
import com.example.erddemo.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for client endpoint
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/{phone}")
    private Iterable<Reservation> getReservationsOnPhone(@PathVariable String phone) {
        return service.getReservationsOnPhone(phone);
    }
}
