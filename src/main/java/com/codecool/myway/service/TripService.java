package com.codecool.myway.service;

import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.entities.TripUser;
import com.codecool.myway.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserService userService;


    public List<TripEntity> getInProgressTripsByUser() {
        return tripRepository.findAllByUserOrderByIdDesc(getCurrentUser());
    }

    private TripUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName =  authentication.getPrincipal().toString();
        return userService.getTripUserByUserName(currentUserName);
    }
}