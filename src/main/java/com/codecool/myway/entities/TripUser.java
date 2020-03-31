package com.codecool.myway.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripUser {

    @Id
    @NotBlank
    @Column(nullable = false, unique = true)
    private String userName;

    @NotBlank
    private String hashedPassword;

    @ElementCollection
    @Singular
    @NotEmpty
    private Set<Role> roles;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST})
    @ToString.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<TripEntity> trips;
}
