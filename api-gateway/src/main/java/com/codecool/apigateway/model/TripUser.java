package com.codecool.apigateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripUser {

    private String userName;
    private String hashedPassword;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private String email;
}
