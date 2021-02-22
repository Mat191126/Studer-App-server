package com.codecool.studer.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @NotNull
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
