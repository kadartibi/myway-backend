package com.codecool.tripservice.service;

import com.codecool.tripservice.model.TripUser;
import com.codecool.tripservice.security.JwtRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${userservice.url}")
    private String baseUrl;


    public TripUser getTripUserByUserName(String currentUserName) {
        return null;
    }

    public String getCurrentUsername() {
        return restTemplate.getForEntity(baseUrl + "current-user", String.class).getBody();
    }

    public TripUser findById(String username) {
        ObjectMapper mapper = new ObjectMapper();
        TripUser tripUser = restTemplate.getForEntity(baseUrl + "current-user-object" + username, TripUser.class).getBody();
        return tripUser;
    }


}
