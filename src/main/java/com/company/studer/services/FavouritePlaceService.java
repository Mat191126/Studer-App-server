package com.company.studer.services;

import com.company.studer.entities.FavouritePlace;
import com.company.studer.repositories.FavouritePlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FavouritePlaceService extends CrudService<FavouritePlace, UUID, FavouritePlaceRepository> {

    @Autowired
    public FavouritePlaceService(FavouritePlaceRepository repository) {
        super(repository);
    }

    @Override
    public boolean update(FavouritePlace newFavouritePlaces) {
        Optional<FavouritePlace> oldPlaceOptional = get(newFavouritePlaces.getId());
        if (oldPlaceOptional.isPresent()) {
            FavouritePlace oldFavouritePlace = oldPlaceOptional.get();
            oldFavouritePlace.setUser(newFavouritePlaces.getUser());
            oldFavouritePlace.setPlace(newFavouritePlaces.getPlace());
            add(oldFavouritePlace);
            return true;
        }
        return false;
    }

    @Override
    public final boolean delete(UUID id) {
        Optional<FavouritePlace> object = get(id);
        if (object.isPresent()) {
            FavouritePlace oldObject = object.get();
            oldObject.setActive(false);
            repository.save(oldObject);
            return true;
        }
        return false;
    }

    public Iterable<FavouritePlace> getByUserIdAndActive(UUID id) {
        return repository.getByUserIdAndActive(id, true);
    }

}
