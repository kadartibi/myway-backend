package com.codecool.myway.repositories;

import com.codecool.myway.entities.plannedDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannedDayRepository extends JpaRepository<plannedDayEntity, Long> {
}
