package com.codecool.userservice.security;


import com.codecool.userservice.entity.TripUser;
import com.codecool.userservice.repository.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TripUserRepository tripUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TripUser tripUser = tripUserRepository
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(
                tripUser.getUserName(),
                tripUser.getHashedPassword(),
                tripUser.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList())
        );
    }
}
