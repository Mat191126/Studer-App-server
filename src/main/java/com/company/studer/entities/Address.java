package com.company.studer.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address implements BaseEntityMethods<UUID> {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
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

    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
