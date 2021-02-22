package com.codecool.studer.entities;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class Location implements BaseEntityMethods<UUID> {

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
