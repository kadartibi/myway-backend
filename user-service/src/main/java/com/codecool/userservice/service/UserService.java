package com.codecool.userservice.service;

import com.codecool.myway.controller.dto.UserCredentials;
import com.codecool.myway.entities.Role;
import com.codecool.myway.entities.TripUser;
import com.codecool.myway.repositories.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TripUserRepository tripUserRepository;
    private final PasswordEncoder encoder;

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

    public TripUser register(UserCredentials userCredentials) {
        return register(userCredentials.getUsername(), userCredentials.getPassword());
    }

    public TripUser getTripUserByUserName(String userName) {
        return tripUserRepository.findByUserName(userName);
    }

    public TripUser registerAllData(UserCredentials userCredentials) {
        return register(userCredentials.getUsername(), userCredentials.getPassword(),
                userCredentials.getFirstName(), userCredentials.getLastName(), userCredentials.getEmail());
    }
    public TripUser getCurrentUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName =  authentication.getPrincipal().toString();
        return tripUserRepository.findByUserName(currentUserName);
    }

}
