package com.codecool.myway.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "name is mandatory")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "country is mandatory")
    private String country;

    private String city;

    @NotNull(message = "date of departure is mandatory")
    private LocalDate dateOfDeparture;


    @NotNull(message = "date of return is mandatory")
    private LocalDate dateOfReturn;

    @Singular
    @EqualsAndHashCode.Exclude
    @ElementCollection
    private Set<String> travelTypes;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "trip", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<plannedDayEntity> plannedDays;

    private int rating;

    private int totalCost;
}
