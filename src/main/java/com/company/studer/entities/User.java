package com.company.studer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements BaseEntityMethods<UUID> {

    @Id
    @GeneratedValue
    @Column( columnDefinition = "uuid", updatable = false )
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    private String university;

    private String city;

    @ElementCollection(targetClass = Language.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_languages",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Set<Language> languages;

    private String photo;

    @NotNull
    private String email;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @JsonIgnore
    @NotNull
    private boolean active = true;
}
