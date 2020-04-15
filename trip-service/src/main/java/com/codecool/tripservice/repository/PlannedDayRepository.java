package com.codecool.tripservice.repository;

import com.codecool.myway.entities.PlannedDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlannedDayRepository extends JpaRepository<PlannedDayEntity, Long> {

    @Query("SELECT p FROM PlannedDayEntity p WHERE p.trip = :id")
    List<PlannedDayEntity> listPlannedDaysForTrip(Long id);

}
