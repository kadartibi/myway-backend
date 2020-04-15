package com.codecool.tripservice.repository;



import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.model.TripUser;
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
