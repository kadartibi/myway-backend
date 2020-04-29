package com.codecool.tripservice.service;

import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${apigatewayuser.url}")
    private String baseUrl;

    public List<TripEntity> getInProgressTripsByUser() {
        return tripRepository.findAllByTripUserIdAndDateOfReturnGreaterThan(getCurrentUser(), LocalDate.now());
    }

    public List<TripEntity> getCompletedTripsByUser() {
        return tripRepository.findAllByTripUserIdAndDateOfReturnLessThan(getCurrentUser(), LocalDate.now());
    }

    private String getCurrentUser(){
        return restTemplate.getForEntity(baseUrl, String.class).getBody();
    }

    public void saveNewTripToUser(TripEntity trip) {
        trip.setTripUserId(getCurrentUser());
        trip.createPlannedDaysForTrip();
        tripRepository.save(trip);
    }

    public void createTripCopy(TripEntity trip) {
        trip.setTripUserId(getCurrentUser());
        System.out.println(trip.getPlannedDays());
    }
}