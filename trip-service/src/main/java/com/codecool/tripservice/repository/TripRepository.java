package com.codecool.tripservice.repository;



import com.codecool.tripservice.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    List<TripEntity> findTop5ByOrderByRatingDesc();

    List<TripEntity> findAllByOrderByIdDesc();

    List<TripEntity> findAllByTripUserIdAndDateOfReturnLessThan(String tripUser, LocalDate time);

    List<TripEntity> findAllByTripUserIdOrderByIdDesc(String tripUser);

    List<TripEntity> findAllByTripUserIdAndDateOfReturnGreaterThan(String tripUser, LocalDate time);

    List<TripEntity> findAllByTripUserId(String userName);

    List<TripEntity> findTop5ByOrderByIdDesc();

    List<TripEntity> findAllByCityContaining(String city);

    List<TripEntity> findAllByCountryContaining(String country);

    List<TripEntity> findAllByNameContaining(String name);
//
//    @Query("select p.plannedDays from TripEntity p  ")
//    List<PlannedDayEntity> getPlannedDays();
}
