package com.codecool.tripservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "trips")
public class TripEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Country")
    private String country;

    private String city;

    @NotNull(message = "Date of departure")
    @Column(nullable = false)
    private LocalDate dateOfDeparture;

    @Column(nullable = false)
    @NotNull(message = "Date of return")
    private LocalDate dateOfReturn;

    @Singular
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ElementCollection
    private Set<String> travelTypes;                                                                                                                                                                                         ;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<PlannedDayEntity> plannedDays = new ArrayList<>();

    private int rating;

    @ElementCollection
    @Singular
    @EqualsAndHashCode.Exclude
    private Set<String> ratings;

    private String tripUserId;

    public void createPlannedDaysForTrip() {
        List<PlannedDayEntity> plannedDayEntitiesPreparation = new ArrayList<>();
        List<LocalDate> plannedDaysDates = dateOfDeparture.datesUntil(dateOfReturn).collect(Collectors.toList());
        for (LocalDate date : plannedDaysDates) {
            plannedDayEntitiesPreparation.add(PlannedDayEntity.builder().date(date).trip(this).build());
        }
        plannedDayEntitiesPreparation.add(PlannedDayEntity.builder().date(dateOfReturn).trip(this).build());
        this.setPlannedDays(plannedDayEntitiesPreparation);
    }

    public void setRating() {
        rating = ratings.size();
    }

    public void saveUserToRatings(String userName) {
        ratings.add(userName);
        setRating();
    }
}
