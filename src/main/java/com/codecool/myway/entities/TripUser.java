package com.codecool.myway.entities;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripUser {

    @Id
    private String userName;

    @NotBlank
    private String hashedPassword;

    @ElementCollection
    @Singular
    @NotEmpty
    private Set<Role> roles;
}
