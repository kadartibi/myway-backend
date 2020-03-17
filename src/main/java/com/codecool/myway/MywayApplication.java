package com.codecool.myway;

import com.codecool.myway.dao.TripStorage;
import com.codecool.myway.entities.TravelTypes;
import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.repositories.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
public class MywayApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(MywayApplication.class);

    @Autowired
    private TripStorage tripStorage;

    @Autowired
    private TripRepository tripRepository;

    public static void main(String[] args) {
        SpringApplication.run(MywayApplication.class, args);
    }

    @PostConstruct
    public void addHardCodedTripsAfterInit() {
        tripStorage.addStarterTrips();
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            TripEntity trip1 = TripEntity.builder()
                    .name("Spanish beaches")
                    .country("Spain")
                    .travelType("Bicycle")
                    .travelType("Train")
                    .dateOfDeparture(LocalDate.of(2015,12,12))
                    .dateOfReturn(LocalDate.of(2016,1,5))
                    .build();

            tripRepository.save(trip1);
        };
    }

}
