package com.codecool.tripservice.service;

import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final TripRepository tripRepository;

    private final UserClientService userClientService;

    @Bean
    public CommandLineRunner createTrips() {

        return args -> {
            TripEntity trip1 = TripEntity.builder()
                    .name("Spanish beaches")
                    .country("Spain")
                    .travelType("Bicycle")
                    .rating(139)
                    .dateOfDeparture(LocalDate.of(2019, 6, 20))
                    .dateOfReturn(LocalDate.of(2019, 6, 30))
                    .tripUserId("user")
                    .build();

            TripEntity trip2 = TripEntity.builder()
                    .name("Canary Dream")
                    .country("Fuerteventura")
                    .travelType("Bicycle")
                    .travelType("Plane")
                    .rating(87)
                    .dateOfDeparture(LocalDate.of(2020, 1, 15))
                    .dateOfReturn(LocalDate.of(2020, 1, 24))
                    .tripUserId("admin")
                    .build();

            TripEntity trip3 = TripEntity.builder()
                    .name("Hungarian retro")
                    .country("Hungary")
                    .travelType("Own car")
                    .rating(70)
                    .dateOfDeparture(LocalDate.of(2019, 11, 21))
                    .dateOfReturn(LocalDate.of(2019, 11, 30))
                    .tripUserId("admin")
                    .build();

            TripEntity trip4 = TripEntity.builder()
                    .name("The Great of Britain")
                    .country("England")
                    .travelType("Bicycle")
                    .travelType("Train")
                    .rating(45)
                    .dateOfDeparture(LocalDate.of(2020, 2, 10))
                    .dateOfReturn(LocalDate.of(2020, 2, 18))
                    .tripUserId("admin")
                    .build();

            TripEntity trip5 = TripEntity.builder()
                    .name("German Cold")
                    .country("Germany")
                    .travelType("Own car")
                    .rating(51)
                    .dateOfDeparture(LocalDate.of(2020, 2, 2))
                    .dateOfReturn(LocalDate.of(2020, 2, 10))
                    .tripUserId("admin")
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
                    .tripUserId("user")
                    .build();

            List<TripEntity> trips = Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip6);
            for (TripEntity trip: trips) {
                trip.createPlannedDaysForTrip();
            }
            tripRepository.saveAll(trips);
        };
    }
}