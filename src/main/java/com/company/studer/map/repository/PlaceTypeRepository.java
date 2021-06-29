package com.company.studer.map.repository;

import com.company.studer.common.repository.CrudRepositoryMethods;
import com.company.studer.map.entity.PlaceType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceTypeRepository extends CrudRepositoryMethods<PlaceType, Long> {

    Optional<PlaceType> findPlaceTypeByType(String type);
}
