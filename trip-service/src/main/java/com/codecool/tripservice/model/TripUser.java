package com.codecool.tripservice.model;

import lombok.Data;
import java.util.Set;

@Data
public class TripUser {

    private String userName;

    private String hashedPassword;

    private Set<Role> roles;

    private String firstName;

    private String lastName;

    private String email;
}
