package com.codecool.studer.repositories;

import com.codecool.studer.entities.Place;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository extends CrudRepositoryMethods<Place, UUID> {

}
