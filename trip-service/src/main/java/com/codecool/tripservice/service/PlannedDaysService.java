package com.codecool.tripservice.service;

import com.codecool.tripservice.entity.ActivityEntity;
import com.codecool.tripservice.entity.PlannedDayEntity;
import com.codecool.tripservice.repository.ActivityRepository;
import com.codecool.tripservice.repository.PlannedDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlannedDaysService {

    @Autowired
    private PlannedDayRepository plannedDayRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public PlannedDayEntity saveActivityToPlannedDay(Long dayId, ActivityEntity activity) {
        Optional<PlannedDayEntity> plannedDay = plannedDayRepository.findById(dayId);
        plannedDay.ifPresent(activity::setPlannedDay);
        activityRepository.save(activity);
        return plannedDay.orElse(null);
    }

}
