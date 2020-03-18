package com.codecool.myway.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ActivityEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Double price;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private PlannedDayEntity plannedDay;

}
