package com.codecool.myway.security;

import com.codecool.myway.entities.Role;
import com.codecool.myway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class InitialUserCreator {

    private final UserService userService;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Bean
    public CommandLineRunner createUsers() {
        return args -> {
            userService.register("admin", "admin", Set.of(Role.USER, Role.ADMIN));
            userService.register("user", "user");
        };
    }
}
