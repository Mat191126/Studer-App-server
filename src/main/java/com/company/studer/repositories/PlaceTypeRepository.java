package com.company.studer.repositories;

import com.company.studer.entities.PlaceType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceTypeRepository extends CrudRepositoryMethods<PlaceType, Long> {

    Optional<PlaceType> getPlaceTypeByType(String type);
}
