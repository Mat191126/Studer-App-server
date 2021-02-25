package com.company.studer.repositories;

import com.company.studer.entities.Place;
import com.company.studer.entities.PlaceType;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository extends CrudRepositoryMethods<Place, UUID> {

    Iterable<Place> getPlaceByActiveAndPlaceTypesIn(boolean active, Iterable<PlaceType> placeTypes);

}
