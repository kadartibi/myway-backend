package com.codecool.myway.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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

    private LocalDate dateOfDeparture;

    private LocalDate dateOfReturn;
//
//    @ElementCollection
//    @Singular
//    @EqualsAndHashCode.Exclude
//    @Enumerated(EnumType.STRING)
//    private List<TravelTypes> travelTypes;
    @ElementCollection
    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<plannedDayEntity> plannedDays;

    private int rating;

    private int totalCost;
}
