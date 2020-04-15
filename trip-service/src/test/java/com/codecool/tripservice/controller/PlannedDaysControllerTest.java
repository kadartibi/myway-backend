package com.codecool.tripservice.controller;

import com.codecool.tripservice.repository.PlannedDayRepository;
import com.codecool.tripservice.repository.TripRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class PlannedDaysControllerTest {

    @Autowired
    private PlannedDayRepository plannedDayRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TestEntityManager entityManager;

}