package com.example.erddemo.controller;

import com.example.erddemo.model.Court;
import com.example.erddemo.model.Reservation;
import com.example.erddemo.service.CourtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for courts
 */
@RestController
@RequestMapping("courts")
public class CourtController {
    public CourtController(CourtService service) {
        this.service = service;
    }

    private final CourtService service;

    @GetMapping("/all")
    public Iterable<Court> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Iterable<Reservation> listReservationsById(@PathVariable Long id) {
        return service.listReservationsById(id);
    }

    @PostMapping("/new")
    public Court createCourt(@RequestBody Court court) {
        return service.createCourtWithPrice(court);
    }
}
