package com.codecool.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TripServiceGetter {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${tripservice.url}")
    private String baseUrl;

}
