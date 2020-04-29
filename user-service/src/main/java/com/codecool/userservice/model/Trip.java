package com.codecool.userservice.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Trip {
    private Long id;

    private String name;

    private String country;

    private String city;

    private LocalDate dateOfDeparture;

    private LocalDate dateOfReturn;

    private Set<String> travelTypes;                                                                                                                                                                                         ;

    private List<PlannedDay> plannedDays;

    private int rating;

    private String tripUserId;
}
