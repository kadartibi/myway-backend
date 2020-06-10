package com.codecool.userservice.service;


import com.codecool.userservice.entity.Role;
import com.codecool.userservice.entity.TripUser;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserService userService;

    @Bean
    public CommandLineRunner createTrips() {
        return args -> {
            TripUser admin = userService.register("admin", "admin", "Istvan","Joska",
                    "admin@admin.com",Set.of(Role.USER, Role.ADMIN));
            TripUser user = userService.register("user", "user", "Jakab", "Gipsz",
                    "user@user.com");
            TripUser user1 = userService.register("user1", "user1", "Kolompar", "Lakatos",
                    "user1@user.com");
        };
    }
}