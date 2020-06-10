package com.codecool.tripservice.repository;



import com.codecool.tripservice.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    List<TripEntity> findTop5ByOrderByRatingDescNameDesc();

    List<TripEntity> findAllByOrderByIdDesc();

    List<TripEntity> findAllByTripUserIdAndDateOfReturnLessThan(String tripUser, LocalDate time);

    List<TripEntity> findAllByTripUserIdAndDateOfReturnGreaterThan(String tripUser, LocalDate time);

    List<TripEntity> findAllByTripUserId(String userName);

    TripEntity getById(Long tripId);

    List<TripEntity> findTop5ByOrderByIdDesc();

    List<TripEntity> findAllByCityContaining(String city);

    List<TripEntity> findAllByCountryContaining(String country);

    List<TripEntity> findAllByNameContaining(String name);
}
