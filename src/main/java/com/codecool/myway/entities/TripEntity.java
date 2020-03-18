package com.codecool.myway.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "name is mandatory")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "country is mandatory")
    private String country;

    private String city;

    @NotNull(message = "date of departure is mandatory")
    @Column(nullable = false)
    private LocalDate dateOfDeparture;

    @Column(nullable = false)

    @NotNull(message = "date of return is mandatory")
    private LocalDate dateOfReturn;

    @Singular
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ElementCollection
    private Set<String> travelTypes;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<PlannedDayEntity> plannedDays;

    private int rating;

    private int totalCost;

    public void createPlannedDaysForTrip() {
        List<PlannedDayEntity> plannedDayEntitiesPreparation = new ArrayList<>();
        List<LocalDate> plannedDaysDates = dateOfDeparture.datesUntil(dateOfReturn).collect(Collectors.toList());
        for (LocalDate date : plannedDaysDates) {
            plannedDayEntitiesPreparation.add(PlannedDayEntity.builder().date(date).trip(this).build());
        }
        plannedDayEntitiesPreparation.add(PlannedDayEntity.builder().date(dateOfReturn).trip(this).build());
        this.setPlannedDays(plannedDayEntitiesPreparation);
    }
}
