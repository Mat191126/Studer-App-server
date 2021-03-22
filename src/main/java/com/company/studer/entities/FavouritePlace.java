package com.company.studer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FavouritePlace implements BaseEntityMethods<UUID> {

    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid", updatable = false )
    private UUID id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Place place;

    @JsonIgnore
    @NotNull
    private boolean active = true;
}
