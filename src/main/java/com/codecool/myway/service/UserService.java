package com.codecool.myway.service;

import com.codecool.myway.controller.dto.UserCredentials;
import com.codecool.myway.entities.Role;
import com.codecool.myway.entities.TripUser;
import com.codecool.myway.repositories.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TripUserRepository tripUserRepository;
    private final PasswordEncoder encoder;

    public TripUser register(String username, String password, Set<Role> roles) {
        return tripUserRepository.save(
                TripUser.builder()
                        .userName(username)
                        .hashedPassword(encoder.encode(password))
                        .roles(roles)
                        .build()
        );
    }

    public TripUser register(String username, String password) {
        return tripUserRepository.save(
                TripUser.builder()
                        .userName(username)
                        .hashedPassword(encoder.encode(password))
                        .role(Role.USER)
                        .build()
        );
    }

    public TripUser register(UserCredentials userCredentials) {
        return register(userCredentials.getUsername(), userCredentials.getPassword());
    }

    public TripUser getTripUserByUserName(String userName) {
        return tripUserRepository.findByUserName(userName);
    }

}
