package com.sportflow.booking.repository;

import com.sportflow.booking.model.Court;
import com.sportflow.booking.model.enums.SportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourtRepository extends JpaRepository<Court, UUID> {

    List<Court> findAllBySport(SportType sport);
}
