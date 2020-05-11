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

    public TripUser findById(String username) {
        return restTemplate.getForEntity(baseUrl + "current-user-object/" + username, TripUser.class).getBody();
    }

    public void registerAllData(UserCredentials tripUser) {
        restTemplate.postForEntity(baseUrl + "signup", tripUser, String.class);
    }
}
