package com.codecool.myway.repositories;

import com.codecool.myway.entities.ActivityEntity;
import com.codecool.myway.entities.PlannedDayEntity;
import com.codecool.myway.entities.TripEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {

    private TestEntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PlannedDayRepository plannedDayRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    public void saveOneSimpleTripEntity() {
        TripEntity trip1 = TripEntity.builder()
                .name("Spanish beaches")
                .country("Spain")
                .dateOfDeparture(LocalDate.of(2019, 6, 20))
                .dateOfReturn(LocalDate.of(2019, 6, 30))
                .build();

        tripRepository.save(trip1);

        assertThat(tripRepository.findAll())
                .hasSize(1)
                .containsExactly(trip1);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripNameShouldNotBeNull() {
        TripEntity trip1 = TripEntity.builder()
                .country("Spain")
                .dateOfDeparture(LocalDate.of(2019, 6, 20))
                .dateOfReturn(LocalDate.of(2019, 6, 30))
                .build();

        tripRepository.saveAndFlush(trip1);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripCountryShouldNotBeNull() {
        TripEntity trip1 = TripEntity.builder()
                .name("Spanish Dream")
                .dateOfDeparture(LocalDate.of(2019, 6, 20))
                .dateOfReturn(LocalDate.of(2019, 6, 30))
                .build();

        tripRepository.saveAndFlush(trip1);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripDepartureDateShouldNotBeNull() {
        TripEntity trip1 = TripEntity.builder()
                .name("Spanish Dream")
                .country("Spain")
                .dateOfReturn(LocalDate.of(2019, 6, 30))
                .build();

        tripRepository.saveAndFlush(trip1);
    }

    @Test(expected = ConstraintViolationException.class)
    public void tripReturnDateShouldNotBeNull() {
        TripEntity trip1 = TripEntity.builder()
                .name("Spanish Dream")
                .country("Spain")
                .dateOfDeparture(LocalDate.of(2019, 6, 20))
                .build();

        tripRepository.saveAndFlush(trip1);
    }

    @Test
    public void tripPersistsAndDeletesPlannedDays() {
        TripEntity trip1 = TripEntity.builder()
                .name("Spanish beaches")
                .country("Spain")
                .dateOfDeparture(LocalDate.of(2019, 6, 20))
                .dateOfReturn(LocalDate.of(2019, 6, 30))
                .build();
        trip1.createPlannedDaysForTrip();
        tripRepository.save(trip1);

        assertThat(plannedDayRepository.findAll())
                .hasSize(11)
                .allMatch(plannedDayEntity -> plannedDayEntity.getTrip() == trip1);

        tripRepository.deleteAll();

        assertThat(plannedDayRepository.findAll())
                .isEmpty();
    }

    @Test
    public void plannedDayPersistsAndDeletesActivity() {
        ActivityEntity cinema = ActivityEntity.builder()
                .description("Cinema")
                .price((double) 10)
                .build();

        PlannedDayEntity day1 = PlannedDayEntity.builder()
                .date(LocalDate.of(2020, 3, 17))
                .activity(cinema)
                .build();

        plannedDayRepository.save(day1);

        assertThat(activityRepository.findAll())
                .hasSize(1)
                .containsExactly(cinema);

        plannedDayRepository.deleteAll();

        assertThat(activityRepository.findAll())
                .isEmpty();
    }

    @Test
    public void findTop5ByOrderByRatingDesc() {
        TripEntity trip1 = TripEntity.builder()
                .name("Spanish beaches")
                .country("Spain")
                .travelType("Bicycle")
                .rating(10)
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
                .name("Chinese everywhere")
                .country("China")
                .travelType("Bus")
                .travelType("Train")
                .travelType("Plane")
                .rating(40)
                .dateOfDeparture(LocalDate.of(2020, 10, 20))
                .dateOfReturn(LocalDate.of(2020, 10, 30))
                .build();

        List<TripEntity> trips = Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip6);
        tripRepository.saveAll(trips);

        assertThat(tripRepository.findTop5ByOrderByRatingDesc())
                .hasSize(5)
                .startsWith(trip2)
                .doesNotContain(trip1);
    }

    @Test
    public void findAllByOrderByIdDesc() {
        TripEntity trip1 = TripEntity.builder()
                .name("Spanish beaches")
                .country("Spain")
                .travelType("Bicycle")
                .rating(10)
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
                .name("Chinese everywhere")
                .country("China")
                .travelType("Bus")
                .travelType("Train")
                .travelType("Plane")
                .rating(40)
                .dateOfDeparture(LocalDate.of(2020, 10, 20))
                .dateOfReturn(LocalDate.of(2020, 10, 30))
                .build();

        List<TripEntity> trips = Arrays.asList(trip1, trip2, trip3, trip4, trip5, trip6);

        tripRepository.saveAll(trips);

        assertThat(tripRepository.findAllByOrderByIdDesc())
                .hasSize(6)
                .startsWith(trip6)
                .endsWith(trip1);
    }
}