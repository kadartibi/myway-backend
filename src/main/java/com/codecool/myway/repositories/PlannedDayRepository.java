package com.codecool.myway.repositories;

import com.codecool.myway.entities.PlannedDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannedDayRepository extends JpaRepository<PlannedDayEntity, Long> {
}
