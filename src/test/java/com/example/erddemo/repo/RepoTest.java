package com.example.erddemo.repo;

import com.example.erddemo.model.Client;
import com.example.erddemo.model.Reservation;
import com.example.erddemo.model.ReservationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class RepoTest {
    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Test
    public void saveReservationAndClient() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            ReservationRequest request = ReservationRequest.builder()
                    .phone("+420 739 393 699")
                    .name("Filip Kollar")
                    .courtID(1L)
                    .from(dateFormat.parse("16:30"))
                    .to(dateFormat.parse("18:30"))
                    .players(2)
                    .build();
            reservationRepo.save(new Reservation(request));
            clientRepo.save(new Client(request));

            assert clientRepo.findAll().iterator().next().getReservationSet().size() == 1;
        } catch (ParseException e) {
            throw new RuntimeException("test couldn't parse date");
        }
    }


    @Test
    public void saveMultipleSameClient() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            ReservationRequest.ReservationRequestBuilder req = ReservationRequest.builder()
                    .phone("+420 739 393 699")
                    .name("Filip Kollar")
                    .courtID(1L)
                    .from(dateFormat.parse("16:30"))
                    .to(dateFormat.parse("18:30"))
                    .players(2);
            List<ReservationRequest> all = List.of(req.courtID(1L).build(),
                    req.courtID(2L).build(),
                    req.courtID(3L).build());


            reservationRepo.saveAll(all.stream().map(Reservation::new).collect(Collectors.toList()));

            clientRepo.save(new Client(req.build()));

            assert clientRepo.findAll().iterator().next().getReservationSet().size() == 3;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}