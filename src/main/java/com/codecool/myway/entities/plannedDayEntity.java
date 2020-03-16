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
public class plannedDayEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    private TripEntity trip;

    @ElementCollection
    @Singular
    @OneToMany(mappedBy = "plannedDay", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    private Set<ActivityEntity> activities;
}
