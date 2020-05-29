package com.codecool.tripservice.service;

import com.codecool.tripservice.entity.ActivityEntity;
import com.codecool.tripservice.entity.PlannedDayEntity;
import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final TripRepository tripRepository;

    @Bean
    public CommandLineRunner createTrips() {

        return args -> {
            TripEntity trip1 = TripEntity.builder()
                    .name("Spanish beaches")
                    .country("Spain")
                    .travelType("Bicycle")
                    .travelType("Plane")
                    .ratings(Arrays.asList("admin", "user1"))
                    .dateOfDeparture(LocalDate.of(2019, 6, 20))
                    .dateOfReturn(LocalDate.of(2019, 6, 23))
                    .tripUserId("user")
                    .build();

            TripEntity trip2 = TripEntity.builder()
                    .name("Canary Dream")
                    .country("Fuerteventura")
                    .travelType("Bicycle")
                    .travelType("Plane")
                    .rating("user")
                    .dateOfDeparture(LocalDate.of(2020, 1, 15))
                    .dateOfReturn(LocalDate.of(2020, 1, 22))
                    .tripUserId("admin")
                    .build();

            TripEntity trip3 = TripEntity.builder()
                    .name("Hungarian retro")
                    .country("Hungary")
                    .travelType("Own car")
                    .dateOfDeparture(LocalDate.of(2019, 11, 21))
                    .dateOfReturn(LocalDate.of(2019, 11, 24))
                    .tripUserId("admin")
                    .build();

            TripEntity trip4 = TripEntity.builder()
                    .name("The Great of Britain")
                    .country("England")
                    .travelType("Bicycle")
                    .travelType("Train")
                    .dateOfDeparture(LocalDate.of(2020, 2, 10))
                    .dateOfReturn(LocalDate.of(2020, 2, 13))
                    .tripUserId("user")
                    .build();

            TripEntity trip5 = TripEntity.builder()
                    .name("German Cold")
                    .country("Germany")
                    .travelType("Own car")
                    .dateOfDeparture(LocalDate.of(2020, 2, 2))
                    .dateOfReturn(LocalDate.of(2020, 2, 5))
                    .tripUserId("admin")
                    .build();

            TripEntity trip6 = TripEntity.builder()
                    .name("Chinese culture")
                    .country("China")
                    .travelType("Bus")
                    .travelType("Train")
                    .travelType("Plane")
                    .dateOfDeparture(LocalDate.of(2020, 10, 20))
                    .dateOfReturn(LocalDate.of(2020, 10, 25))
                    .tripUserId("user")
                    .build();

            TripEntity trip7 = TripEntity.builder()
                    .name("The True Paradise")
                    .country("Spain")
                    .city("Menorca")
                    .travelType("Walk")
                    .travelType("RentalCar")
                    .travelType("Plane")
                    .rating("user")
                    .dateOfDeparture(LocalDate.of(2019, 7, 20))
                    .dateOfReturn(LocalDate.of(2019, 7, 25))
                    .tripUserId("user1")
                    .build();

            TripEntity trip8 = TripEntity.builder()
                    .name("Trip to the Freezer")
                    .country("Russia")
                    .city("Moscow")
                    .travelType("RentalCar")
                    .travelType("Train")
                    .travelType("Plane")
                    .dateOfDeparture(LocalDate.of(2020, 1, 5))
                    .dateOfReturn(LocalDate.of(2020, 1, 8))
                    .tripUserId("user1")
                    .build();

            TripEntity trip9 = TripEntity.builder()
                    .name("Into the contryside")
                    .country("Hungary")
                    .city("Komloska")
                    .travelType("Bus")
                    .travelType("Walk")
                    .travelType("Bicycle")
                    .dateOfDeparture(LocalDate.of(2020, 4, 9))
                    .dateOfReturn(LocalDate.of(2020, 4, 12))
                    .tripUserId("user1")
                    .build();

            List<TripEntity> trips = Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip6, trip7, trip8, trip9);
            for (TripEntity trip: trips) {
                trip.setRating();
                trip.createPlannedDaysForTrip();
                for (PlannedDayEntity plannedDay : trip.getPlannedDays()) {
                    plannedDay.setActivities(activityCreator(plannedDay));
                }
            }
            tripRepository.saveAll(trips);
        };
    }

    private HashSet<ActivityEntity> activityCreator(PlannedDayEntity plannedDay) {
        HashSet<ActivityEntity> activityList = new HashSet<>();
        List<String> howList = Arrays.asList("going to ", "visit ", "travel to ", "checkout ");
        List<String> whatList = Arrays.asList("museum", "pub", "cathedral", "disco", "beach", "statue");
        double newDouble = new Random().nextInt(10) + 1;
        for (int i = 0; i < 3; i++) {
            String newDescription = howList.get(new Random().nextInt(howList.size())) +
                    whatList.get(new Random().nextInt(whatList.size()));
            ActivityEntity activity = ActivityEntity.builder()
                                                    .description(newDescription)
                                                    .price(newDouble)
                                                    .plannedDay(plannedDay)
                                                    .build();
            activityList.add(activity);
        }
        return activityList;
    }
}