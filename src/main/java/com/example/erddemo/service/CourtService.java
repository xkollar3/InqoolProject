package com.example.erddemo.service;

import com.example.erddemo.model.Court;
import com.example.erddemo.model.Reservation;
import com.example.erddemo.repo.CourtRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service for courts
 */
@Service
public class CourtService {
    private final CourtRepo courtRepo;

    public CourtService(CourtRepo courtRepo) {
        this.courtRepo = courtRepo;
    }


    public Iterable<Court> list() {
        return courtRepo.findAll();
    }

    /**
     * List all reservations with id
     * @param courtId id of court
     * @return Iterable of reservations
     */
    public Iterable<Reservation> listReservationsById(Long courtId) {

        Court court = courtRepo.findById(courtId).orElse(null);
        if (court == null) {
            return new ArrayList<>();
        }
        else {
            return court.getCourt_reservations();
        }
    }

    /**
     * Save a court
     *
     * @param court to be created
     * @return court
     */
    public Court createCourtWithPrice(Court court) {
        return courtRepo.save(court);
    }
}
