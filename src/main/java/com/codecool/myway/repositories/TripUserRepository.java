package com.codecool.myway.repositories;

import com.codecool.myway.entities.TripUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripUserRepository extends JpaRepository<TripUser, String> {

    TripUser findByUserName(String userName);
}
