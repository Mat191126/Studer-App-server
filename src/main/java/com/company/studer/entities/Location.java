package com.company.studer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location implements BaseEntityMethods<UUID> {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private UUID id;

    @NotNull
    private BigDecimal latitude;
    @NotNull
    private BigDecimal longitude;

    @JsonIgnore
    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
