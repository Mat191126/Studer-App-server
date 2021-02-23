package com.codecool.studer.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Place {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String address;     //temporary type of String

    @NotNull
    @ElementCollection(targetClass = PlaceType.class)
    @CollectionTable(name = "place_types",
            joinColumns = @JoinColumn(name = "place_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Set<PlaceType> placeTypes;

    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}