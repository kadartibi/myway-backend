package com.codecool.userservice.repository;


import com.codecool.userservice.entity.TripUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripUserRepository extends JpaRepository<TripUser, String> {

    TripUser findByUserName(String userName);

    @Query("SELECT u.userName FROM TripUser u")
    List<String> getAllUserNames();

}
