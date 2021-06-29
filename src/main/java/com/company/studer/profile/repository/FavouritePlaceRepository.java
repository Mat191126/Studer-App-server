package com.company.studer.profile.repository;

import com.company.studer.common.repository.CrudRepositoryMethods;
import com.company.studer.profile.entity.FavouritePlace;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavouritePlaceRepository extends CrudRepositoryMethods<FavouritePlace, UUID> {
    Iterable<FavouritePlace> findByUserIdAndActive(UUID id, boolean active);
}
