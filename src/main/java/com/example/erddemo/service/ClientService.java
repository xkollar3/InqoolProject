package com.example.erddemo.service;

import com.example.erddemo.model.Client;
import com.example.erddemo.model.Reservation;
import com.example.erddemo.repo.ClientRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Client service
 */
@Service
public class ClientService {
    private final ClientRepo clientRepo;

    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    /**
     * Get all reservations associated with phone number
     * @param phone a phone number
     * @return Iterable of reservations
     */
    public Iterable<Reservation> getReservationsOnPhone(String phone) {
        Client client = clientRepo.findById(phone).orElse(null);
        if (client != null) {
            return client.getReservationSet();
        }
        else {
            return new ArrayList<>();
        }
    }
}
