package com.company.studer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FavouritePlace implements BaseEntityMethods<UUID> {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue
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
