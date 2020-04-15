package com.codecool.tripservice.service;

import com.codecool.tripservice.model.TripUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
