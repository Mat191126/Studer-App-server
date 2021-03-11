package com.company.studer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location implements BaseEntityMethods<UUID> {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue
    private UUID id;

    @NotNull
    @Column(precision = 8, scale = 6)
    private BigDecimal latitude;

    @NotNull
    @Column(precision = 8, scale = 6)
    private BigDecimal longitude;

    @JsonIgnore
    @NotNull
    private boolean active = true;
}
