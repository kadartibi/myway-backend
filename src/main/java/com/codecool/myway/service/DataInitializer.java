package com.codecool.myway.service;

import com.codecool.myway.entities.Role;
import com.codecool.myway.entities.TripEntity;
import com.codecool.myway.entities.TripUser;
import com.codecool.myway.repositories.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final TripRepository tripRepository;

    private final UserService userService;

    @Bean
    public CommandLineRunner createTrips() {
        TripUser admin = userService.register("admin", "admin", "Istvan","Joska",
                "admin@admin.com",Set.of(Role.USER, Role.ADMIN));
        TripUser user = userService.register("user", "user", "Jakab", "Gipsz",
                "user@user.com");

        return args -> {
            TripEntity trip1 = TripEntity.builder()
                    .name("Spanish beaches")
                    .country("Spain")
                    .travelType("Bicycle")
                    .rating(139)
                    .dateOfDeparture(LocalDate.of(2019, 6, 20))
                    .dateOfReturn(LocalDate.of(2019, 6, 30))
                    .user(user)
                    .build();

            TripEntity trip2 = TripEntity.builder()
                    .name("Canary Dream")
                    .country("Fuerteventura")
                    .travelType("Bicycle")
                    .travelType("Plane")
                    .rating(87)
                    .dateOfDeparture(LocalDate.of(2020, 1, 15))
                    .dateOfReturn(LocalDate.of(2020, 1, 24))
                    .user(user)
                    .build();

            TripEntity trip3 = TripEntity.builder()
                    .name("Hungarian retro")
                    .country("Hungary")
                    .travelType("Own car")
                    .rating(70)
                    .dateOfDeparture(LocalDate.of(2019, 11, 21))
                    .dateOfReturn(LocalDate.of(2019, 11, 30))
                    .user(admin)
                    .build();

            TripEntity trip4 = TripEntity.builder()
                    .name("The Great of Britain")
                    .country("England")
                    .travelType("Bicycle")
                    .travelType("Train")
                    .rating(45)
                    .dateOfDeparture(LocalDate.of(2020, 2, 10))
                    .dateOfReturn(LocalDate.of(2020, 2, 18))
                    .user(admin)
                    .build();

            TripEntity trip5 = TripEntity.builder()
                    .name("German Cold")
                    .country("Germany")
                    .travelType("Own car")
                    .rating(51)
                    .dateOfDeparture(LocalDate.of(2020, 2, 2))
                    .dateOfReturn(LocalDate.of(2020, 2, 10))
                    .user(admin)
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
                    .user(admin)
                    .build();

            List<TripEntity> trips = Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip6);
            for (TripEntity trip: trips) {
                trip.createPlannedDaysForTrip();
            }
            tripRepository.saveAll(trips);
        };
    }
}