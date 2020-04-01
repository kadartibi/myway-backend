package com.codecool.myway.repositories;

import com.codecool.myway.entities.PlannedDayEntity;
import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.entities.TripUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    List<TripEntity> findTop5ByOrderByRatingDesc();

    List<TripEntity> findAllByOrderByIdDesc();

    List<TripEntity> findAllByDateOfReturnLessThan(LocalDate time);

    List<TripEntity> findAllByUserOrderByIdDesc(TripUser tripUser);
//
//    @Query("select p.plannedDays from TripEntity p  ")
//    List<PlannedDayEntity> getPlannedDays();
}
