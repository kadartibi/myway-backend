package com.codecool.myway;

import com.codecool.myway.dao.TripStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MywayApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(MywayApplication.class);

    @Autowired
    private TripStorage tripStorage;

    public static void main(String[] args) {
        SpringApplication.run(MywayApplication.class, args);
    }

    @PostConstruct
    public void addHardCodedTripsAfterInit() {
        LOGGER.info(tripStorage.getTrips().toString());
        tripStorage.addStarterTrips();
    }

}
