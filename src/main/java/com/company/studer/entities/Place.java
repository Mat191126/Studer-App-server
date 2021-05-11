package com.company.studer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Place implements BaseEntityMethods<UUID> {

    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid", updatable = false )
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @OneToOne
    private Address address;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "place_types_list", joinColumns = {
            @JoinColumn(name = "place_id")}, inverseJoinColumns = {
            @JoinColumn(name = "place_type_id")})
    private Set<PlaceType> placeTypes;

    @JsonIgnore
    @NotNull
    private boolean active = true;
}
