package com.codecool.myway.repositories;

import com.codecool.myway.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
}
