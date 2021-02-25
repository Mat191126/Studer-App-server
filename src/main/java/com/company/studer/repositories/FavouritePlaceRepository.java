package com.company.studer.repositories;

import com.company.studer.entities.FavouritePlace;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavouritePlaceRepository extends CrudRepositoryMethods<FavouritePlace, UUID>, SelectableByUserIdAndActive<FavouritePlace, UUID> {
    Iterable<FavouritePlace> getByUserIdAndActive(UUID id, boolean active);
}
