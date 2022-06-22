package com.example.erddemo.repo;

import com.example.erddemo.model.Court;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepo extends CrudRepository<Court, Long> {
}
