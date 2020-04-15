package com.codecool.tripservice.service;


import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.model.TripUser;
import com.codecool.tripservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserService userService;


    public List<TripEntity> getInProgressTripsByUser() {
        return tripRepository.findAllByUserAndDateOfReturnGreaterThan(getCurrentUser(), LocalDate.now());
    }

    public List<TripEntity> getCompletedTripsByUser() {
        return tripRepository.findAllByUserAndDateOfReturnLessThan(getCurrentUser(), LocalDate.now());
    }

    private TripUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName =  authentication.getPrincipal().toString();
        return userService.getTripUserByUserName(currentUserName);
    }

    public void addUserToTrip(TripEntity trip) {
        trip.setUser(getCurrentUser());
    }
}