package com.company.studer.repositories;

import com.company.studer.entities.Place;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository extends CrudRepositoryMethods<Place, UUID> {

}
