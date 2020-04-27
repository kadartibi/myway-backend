package com.codecool.apigateway.service;

import com.codecool.apigateway.controller.dto.UserCredentials;
import com.codecool.apigateway.model.TripUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TripUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${userservice.url}")
    private String baseUrl;

    public Optional<TripUser> findById(String username) {
        Optional<TripUser> user = restTemplate.getForEntity(baseUrl + "current-user-object/" + username, Optional.class).getBody();
        return user;
    }

    public void registerAllData(UserCredentials tripUser) {
        restTemplate.postForEntity(baseUrl + "signup", tripUser, String.class);
    }

    public Optional<TripUser> getCurrentUserDetails(String username) {
        Optional<TripUser> user = restTemplate.getForEntity(baseUrl + "current-user-object/" + username, Optional.class).getBody();
        return user;
    }
}
