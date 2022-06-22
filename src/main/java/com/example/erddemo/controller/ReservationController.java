package com.example.erddemo.controller;

import com.example.erddemo.model.Reservation;
import com.example.erddemo.model.ReservationRequest;
import com.example.erddemo.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for reservations
 */
@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("")
    public Iterable<Reservation> list() {
        return service.list();
    }

    @PostMapping("/new")
    public ResponseEntity<Double> addRes(@RequestBody ReservationRequest request) {
        Reservation reservation = service.addRes(request);

        if (reservation == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        else {
            return new ResponseEntity<>(reservation.getPrice(), HttpStatus.OK);
        }
    }
}
