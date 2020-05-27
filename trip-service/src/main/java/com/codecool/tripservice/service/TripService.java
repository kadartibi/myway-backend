package com.codecool.tripservice.service;

import com.codecool.tripservice.entity.ActivityEntity;
import com.codecool.tripservice.entity.PlannedDayEntity;
import com.codecool.tripservice.entity.TripEntity;
import com.codecool.tripservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${apigatewayuser.url}")
    private String baseUrl;

    public List<TripEntity> getInProgressTripsByUser(HttpServletRequest request) {
        return tripRepository.findAllByTripUserIdAndDateOfReturnGreaterThan(getCurrentUser(request), LocalDate.now());
    }

    public List<TripEntity> getCompletedTripsByUser(HttpServletRequest request) {
        return tripRepository.findAllByTripUserIdAndDateOfReturnLessThan(getCurrentUser(request), LocalDate.now());
    }

    private String getCurrentUser(HttpServletRequest request) {
        String jwtToken = request.getCookies()[0].getValue();
        return restTemplate.getForEntity(baseUrl + jwtToken, String.class).getBody();
    }

    public void saveNewTripToUser(TripEntity trip, HttpServletRequest request) {
        trip.setTripUserId(getCurrentUser(request));
        trip.createPlannedDaysForTrip();
        tripRepository.save(trip);
    }

    public void createTripCopy(Long tripId, LocalDate newStartingDate, HttpServletRequest request) {
        int index = 0;
        TripEntity tripToCopy = tripRepository.findById(tripId).orElse(null);
        assert tripToCopy != null;
        LocalDate newReturnDate = newStartingDate.plusDays(tripToCopy.getPlannedDays().size());
        List<PlannedDayEntity> plannedDayEntitiesToCopy = new ArrayList<>();
        List<LocalDate> plannedDaysDates = newStartingDate.datesUntil(newReturnDate).collect(Collectors.toList());
        TripEntity tripCopy = TripEntity.builder()
                .tripUserId(getCurrentUser(request))
                .travelTypes(tripToCopy.getTravelTypes())
                .country(tripToCopy.getCountry())
                .dateOfDeparture(newStartingDate)
                .dateOfReturn(newReturnDate)
                .name(tripToCopy.getName())
                .rating(0)
                .city(tripToCopy.getCity())
                .build();
        for (PlannedDayEntity plannedDay : tripToCopy.getPlannedDays()) {
            Set<ActivityEntity> activityEntitiesToCopy = new HashSet<>();
            PlannedDayEntity plannedDayCopy = PlannedDayEntity.builder()
                    .date(plannedDaysDates.get(index))
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
            index ++;
        }
        tripCopy.setPlannedDays(plannedDayEntitiesToCopy);
        tripRepository.save(tripCopy);
    }

}