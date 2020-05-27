package com.codecool.tripservice.repository;



import com.codecool.tripservice.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    List<TripEntity> findTop5ByOrderByRatings();

    List<TripEntity> findTop5ByOrderByRatingDesc();

    List<TripEntity> findAllByOrderByIdDesc();

    List<TripEntity> findAllByTripUserIdAndDateOfReturnLessThan(String tripUser, LocalDate time);

    List<TripEntity> findAllByTripUserIdOrderByIdDesc(String tripUser);

    List<TripEntity> findAllByTripUserIdAndDateOfReturnGreaterThan(String tripUser, LocalDate time);

    List<TripEntity> findAllByTripUserId(String userName);

    List<TripEntity> findTop5ByOrderByIdDesc();
//
//    @Query("select p.plannedDays from TripEntity p  ")
//    List<PlannedDayEntity> getPlannedDays();
}
