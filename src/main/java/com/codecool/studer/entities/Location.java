package com.codecool.studer.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull
    private BigDecimal latitude;
    @NotNull
    private BigDecimal longitude;

    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
