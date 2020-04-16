package com.codecool.userservice.controller;

import com.codecool.userservice.service.TripServiceGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
@RequestMapping
public class TripController {

    @Autowired
    private TripServiceGetter tripServiceGetter;

    @Value("${userservice.url}")
    private String baseUrl;

}
