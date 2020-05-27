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

    @Query("SELECT t.tripUserId from TripEntity t")
    List<String> getUsersList();

//    @Query("UPDATE TripEntity t SET t.ratings = (t.ratings + :userName) WHERE t.id = :tripId")
//    void saveRecommendation(int tripId, String userName);

    TripEntity getById(Long tripId);

//    @Query("UPDATE TripEntity t SET t = :trip WHERE t.id = :tripId")
//    void saveUpdatedTrip(TripEntity trip, Long tripId);

//    @Query("UPDATE TripEntity t SET t.ratings = :newRatings where t.id = :tripId")
//    void saveRecommendation(Set<String> newRatings, Long tripId);
//
//    @Query("UPDATE TripEntity_ratings SET ratings = :userName WHERE TripEntity_id = :tripId")
//    void saveRecommendation(String userName, Long tripId);
//
//    @Query("select p.plannedDays from TripEntity p  ")
//    List<PlannedDayEntity> getPlannedDays();
}
