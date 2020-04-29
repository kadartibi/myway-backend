package com.codecool.userservice.model;

import java.time.LocalDate;
import java.util.Set;

public class PlannedDay {

    private Long id;

    private LocalDate date;

    private Trip trip;

    private Set<Activity> activities;
}
