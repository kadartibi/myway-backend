package com.codecool.tripservice.repository;

import com.codecool.myway.entities.PlannedDayEntity;
import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.entities.TripUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    List<TripEntity> findTop5ByOrderByRatingDesc();

    List<TripEntity> findAllByOrderByIdDesc();

    List<TripEntity> findAllByUserAndDateOfReturnLessThan(TripUser tripUser, LocalDate time);

    List<TripEntity> findAllByUserOrderByIdDesc(TripUser tripUser);

    List<TripEntity> findAllByUserAndDateOfReturnGreaterThan(TripUser tripUser, LocalDate time);
//
//    @Query("select p.plannedDays from TripEntity p  ")
//    List<PlannedDayEntity> getPlannedDays();
}
