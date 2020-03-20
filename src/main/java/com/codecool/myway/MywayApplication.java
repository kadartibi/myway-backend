package com.codecool.myway;

import com.codecool.myway.dao.TripStorage;
import com.codecool.myway.entities.TravelTypes;
import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.repositories.TripRepository;
import org.hibernate.Hibernate;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class MywayApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(MywayApplication.class);

//    @Autowired
//    private TripStorage tripStorage;

    @Autowired
    private TripRepository tripRepository;

    public static void main(String[] args) {
        SpringApplication.run(MywayApplication.class, args);
    }

//    @PostConstruct
//    public void addHardCodedTripsAfterInit() {
//        tripStorage.addStarterTrips();
//    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        Set<String> travelTypes = new HashSet<>();
        travelTypes.add("Bicycle");
        return args -> {
            TripEntity trip1 = TripEntity.builder()
                    .name("Spanish beaches")
                    .country("Spain")
                    .travelType("Bicycle")
                    .rating(139)
                    .dateOfDeparture(LocalDate.of(2019, 6, 20))
                    .dateOfReturn(LocalDate.of(2019, 6, 30))
                    .build();

            TripEntity trip2 = TripEntity.builder()
                    .name("Canary Dream")
                    .country("Fuerteventura")
                    .travelType("Bicycle")
                    .travelType("Plane")
                    .rating(87)
                    .dateOfDeparture(LocalDate.of(2020, 1, 15))
                    .dateOfReturn(LocalDate.of(2020, 1, 24))
                    .build();

            TripEntity trip3 = TripEntity.builder()
                    .name("Hungarian retro")
                    .country("Hungary")
                    .travelType("Own car")
                    .rating(70)
                    .dateOfDeparture(LocalDate.of(2019, 11, 21))
                    .dateOfReturn(LocalDate.of(2019, 11, 30))
                    .build();

            TripEntity trip4 = TripEntity.builder()
                    .name("The Great of Britain")
                    .country("England")
                    .travelType("Bicycle")
                    .travelType("Train")
                    .rating(45)
                    .dateOfDeparture(LocalDate.of(2020, 2, 10))
                    .dateOfReturn(LocalDate.of(2020, 2, 18))
                    .build();

            TripEntity trip5 = TripEntity.builder()
                    .name("German Cold")
                    .country("Germany")
                    .travelType("Own car")
                    .rating(51)
                    .dateOfDeparture(LocalDate.of(2020, 2, 2))
                    .dateOfReturn(LocalDate.of(2020, 2, 10))
                    .build();

            TripEntity trip6 = TripEntity.builder()
                    .name("Chinese culture")
                    .country("China")
                    .travelType("Bus")
                    .travelType("Train")
                    .travelType("Plane")
                    .rating(9)
                    .dateOfDeparture(LocalDate.of(2020, 10, 20))
                    .dateOfReturn(LocalDate.of(2020, 10, 30))
                    .build();

            List<TripEntity> trips = Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip6);
            for (TripEntity trip: trips) {
                trip.createPlannedDaysForTrip();
            }
            tripRepository.saveAll(trips);
        };
    }
}
