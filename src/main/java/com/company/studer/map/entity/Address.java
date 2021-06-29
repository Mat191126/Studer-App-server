package com.company.studer.map.entity;

import com.company.studer.common.entity.BaseEntityMethods;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address implements BaseEntityMethods<UUID> {

    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid", updatable = false )
    private UUID id;

    @NotNull
    private String street;

    @NotNull
    private String streetNumber;

    @NotNull
    private String town;

    @NotNull
    private String zipCode;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @JsonIgnore
    @NotNull
    private boolean active = true;
}
