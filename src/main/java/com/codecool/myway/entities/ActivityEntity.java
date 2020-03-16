package com.codecool.myway.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private plannedDayEntity plannedDay;

}
