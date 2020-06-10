package com.codecool.tripservice.repository;


import com.codecool.tripservice.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    List<ActivityEntity> findTop5ByOrderByIdDesc();
}
