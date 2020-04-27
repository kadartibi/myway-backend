package com.codecool.apigateway.security;

import com.codecool.apigateway.model.TripUser;
import com.codecool.apigateway.service.TripUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TripUserService tripUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TripUser tripUser = tripUserService
                .findById(username);
        if (tripUser == null) {
            throw new UsernameNotFoundException("Username not found");
        } else {
            tripUser.setUsername(username);
        }

        return new User(
                tripUser.getUsername(),
                tripUser.getHashedPassword(),
                tripUser.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList())
        );
    }
}
