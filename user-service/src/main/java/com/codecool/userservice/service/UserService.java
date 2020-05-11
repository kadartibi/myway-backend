package com.codecool.userservice.service;

import com.codecool.userservice.controller.dto.UserCredentials;
import com.codecool.userservice.entity.Role;
import com.codecool.userservice.entity.TripUser;
import com.codecool.userservice.repository.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TripUserRepository tripUserRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public TripUser register(String username, String password) {
        return tripUserRepository.save(
                TripUser.builder()
                        .userName(username)
                        .hashedPassword(encoder.encode(password))
                        .role(Role.USER)
                        .build()
        );
    }

    public TripUser register(String username, String password, String firstName, String lastName, String email) {
        return tripUserRepository.save(
                TripUser.builder()
                        .userName(username)
                        .hashedPassword(encoder.encode(password))
                        .role(Role.USER)
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .build()
        );
    }

    public TripUser register(String username, String password, String firstName, String lastName, String email, Set<Role> roles) {
        return tripUserRepository.save(
                TripUser.builder()
                        .userName(username)
                        .hashedPassword(encoder.encode(password))
                        .roles(roles)
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .build()
        );
    }

    public TripUser registerAllData(UserCredentials userCredentials) {
        return register(userCredentials.getUsername(), userCredentials.getPassword(),
                userCredentials.getFirstName(), userCredentials.getLastName(), userCredentials.getEmail());
    }

    public TripUser getCurrentUserDetails(String currentUserName){
        return tripUserRepository.findByUserName(currentUserName);
    }

}
