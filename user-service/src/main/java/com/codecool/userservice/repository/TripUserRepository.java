package com.codecool.userservice.repository;


import com.codecool.userservice.entity.TripUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripUserRepository extends JpaRepository<TripUser, String> {

    TripUser findByUserName(String userName);
}
