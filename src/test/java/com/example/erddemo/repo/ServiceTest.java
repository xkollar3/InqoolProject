package com.example.erddemo.repo;

import com.example.erddemo.model.Court;
import com.example.erddemo.model.ReservationRequest;
import com.example.erddemo.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CourtRepo courts;

    @Test
    public void createReservationNonExistentCourt() {
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


            assert reservationService.addRes(request) == null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createReservationOk() {
        courts.save(new Court(10L));

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

            assert reservationService.addRes(request) != null;
        }
        catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void createOverlappingReservation() {
        courts.save(new Court(10L));
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

            assert reservationService.addRes(request) != null;
            assert reservationService.addRes(request) == null;
        } catch (ParseException e) {
            throw new RuntimeException("parse failed");
        }
    }
}
