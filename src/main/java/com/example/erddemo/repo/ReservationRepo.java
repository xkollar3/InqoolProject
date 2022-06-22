package com.example.erddemo.repo;

import com.example.erddemo.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends CrudRepository<Reservation, Long>{
}
