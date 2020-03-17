package com.codecool.myway.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class TripEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    private String city;

    @Column(nullable = false)
    private LocalDate dateOfDeparture;

    @Column(nullable = false)
    private LocalDate dateOfReturn;

    @Singular
    @EqualsAndHashCode.Exclude
    @ElementCollection
    private Set<String> travelTypes;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<PlannedDayEntity> plannedDays;

    private int rating;

    private int totalCost;

    public List<PlannedDayEntity> createPlannedDaysForTrip() {
        List<PlannedDayEntity> plannedDayEntitiesPreparation = new ArrayList<>();
        List<LocalDate> plannedDaysDates = dateOfDeparture.datesUntil(dateOfReturn).collect(Collectors.toList());
        for (LocalDate date : plannedDaysDates) {
            plannedDayEntitiesPreparation.add(PlannedDayEntity.builder().date(date).trip(this).build());
        }
        plannedDayEntitiesPreparation.add(PlannedDayEntity.builder().date(dateOfReturn).trip(this).build());
        return plannedDayEntitiesPreparation;
    }
}
