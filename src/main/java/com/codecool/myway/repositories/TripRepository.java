package com.codecool.myway.repositories;

import com.codecool.myway.entities.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

    List<TripEntity> findTop5ByOrderByRatingDesc();

    List<TripEntity> findAllByOrderByIdDesc();
}
