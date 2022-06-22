package com.example.erddemo.service;

import com.example.erddemo.model.Client;
import com.example.erddemo.model.Court;
import com.example.erddemo.model.Reservation;
import com.example.erddemo.model.ReservationRequest;
import com.example.erddemo.repo.ClientRepo;
import com.example.erddemo.repo.CourtRepo;
import com.example.erddemo.repo.ReservationRepo;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class ReservationService {

    private final ReservationRepo repo;

    private final CourtRepo courts;

    private final ClientRepo clients;

    public ReservationService(ReservationRepo repo, CourtRepo courts, ClientRepo clients) {
        this.repo = repo;
        this.courts = courts;
        this.clients = clients;
    }

    /**
     * List all reservations in the system
     *
     * @return Iterable of reservations
     */
    public Iterable<Reservation> list() {
        return repo.findAll();
    }

    /**
     * Cascades were supposed to take care of this,
     * but they didn't work so it has to access all repos
     *
     * Method creates a reservation if possible
     *
     * @param request for reservation
     * @return Reservation or null depending on success
     */
    public Reservation addRes(ReservationRequest request) {
        Reservation reservation = new Reservation(request);

        Client client = clients.findById(request.getPhone()).orElse(null);
        if (client != null && !client.getFull_name().equals(request.getName())) {
            return null;
        }
        else {
            clients.save(new Client(request));
        }

        Court court = courts.findById(request.getCourtID()).orElse(null);
        if (court == null) {
            return null;
        }

        if (StreamSupport.stream(repo.findAll().spliterator(), false).anyMatch(reservation::equals)) {
            return null;
        }

        reservation.setCourt(court);

        return repo.save(reservation);
    }
}
