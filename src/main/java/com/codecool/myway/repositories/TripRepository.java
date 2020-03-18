package com.codecool.myway.repositories;

import com.codecool.myway.entities.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TripRepository extends JpaRepository<TripEntity, Long> {

//    @Query("select t from TripEntity t join fetch t.travelTypes order by t.rating desc ")
//    List<TripEntity> getTop5Trips();

    List<TripEntity> findTop5ByOrderByRatingDesc();


}
