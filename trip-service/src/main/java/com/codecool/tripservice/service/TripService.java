package com.codecool.tripservice.service;

import com.codecool.tripservice.entity.ActivityEntity;
import com.codecool.tripservice.entity.PlannedDayEntity;
import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.ActivityRepository;
import com.codecool.tripservice.repository.PlannedDayRepository;
import com.codecool.tripservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private PlannedDayRepository plannedDayRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${apigatewayuser.url}")
    private String baseUrl;

    public List<TripEntity> getInProgressTripsByUser() {
        return tripRepository.findAllByTripUserIdAndDateOfReturnGreaterThan(getCurrentUser(), LocalDate.now());
    }

    public List<TripEntity> getCompletedTripsByUser() {
        return tripRepository.findAllByTripUserIdAndDateOfReturnLessThan(getCurrentUser(), LocalDate.now());
    }

    private String getCurrentUser() {
        return restTemplate.getForEntity(baseUrl, String.class).getBody();
    }

    public void saveNewTripToUser(TripEntity trip) {
        trip.setTripUserId(getCurrentUser());
        trip.createPlannedDaysForTrip();
        tripRepository.save(trip);
    }

    public void createTripCopy(Long tripId) {
        TripEntity tripToCopy = tripRepository.findById(tripId).orElse(null);
        assert tripToCopy != null;
        List<PlannedDayEntity> plannedDayEntitiesToCopy = new ArrayList<>();
        TripEntity tripCopy = TripEntity.builder()
                .tripUserId(getCurrentUser())
                .travelTypes(tripToCopy.getTravelTypes())
                .country(tripToCopy.getCountry())
                .dateOfDeparture(tripToCopy.getDateOfDeparture())
                .dateOfReturn(tripToCopy.getDateOfReturn())
                .name(tripToCopy.getName())
                .rating(0)
                .city(tripToCopy.getCity())
                .build();
        for (PlannedDayEntity plannedDay : tripToCopy.getPlannedDays()) {
            Set<ActivityEntity> activityEntitiesToCopy = new HashSet<>();
            PlannedDayEntity plannedDayCopy = PlannedDayEntity.builder()
                    .date(plannedDay.getDate())
                    .trip(tripCopy)
                    .build();
            if (plannedDay.getActivities() != null) {
                for (ActivityEntity activity : plannedDay.getActivities()) {
                    ActivityEntity activityCopy = ActivityEntity.builder()
                            .description(activity.getDescription())
                            .price(activity.getPrice())
                            .plannedDay(plannedDayCopy)
                            .build();
                    activityEntitiesToCopy.add(activityCopy);
                }
            }
            plannedDayCopy.setActivities(activityEntitiesToCopy);
            plannedDayEntitiesToCopy.add(plannedDayCopy);
        }
        tripCopy.setPlannedDays(plannedDayEntitiesToCopy);
        tripRepository.save(tripCopy);
    }

}