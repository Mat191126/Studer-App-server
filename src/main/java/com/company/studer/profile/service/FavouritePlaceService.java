package com.company.studer.profile.service;

import com.company.studer.common.service.CrudService;
import com.company.studer.profile.entity.FavouritePlace;
import com.company.studer.profile.repository.FavouritePlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FavouritePlaceService extends CrudService<FavouritePlace, UUID> {

    private final FavouritePlaceRepository repository;

    @Autowired
    public FavouritePlaceService(FavouritePlaceRepository repository) {
        super(repository);
        this.repository = repository;
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
    public boolean delete(UUID id) {
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
        return repository.findByUserIdAndActive(id, true);
    }

}
