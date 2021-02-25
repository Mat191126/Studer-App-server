package com.company.studer.services;

import com.company.studer.entities.Place;
import com.company.studer.entities.PlaceType;
import com.company.studer.repositories.CrudRepositoryMethods;
import com.company.studer.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class PlaceService extends CrudService<Place, UUID> {

    private final AddressService addressService;

    @Autowired
    public PlaceService(CrudRepositoryMethods<Place, UUID> repository, AddressService addressService) {
        super(repository);
        this.addressService = addressService;
    }

    @Override
    public boolean update(Place newPlace) {
        Optional<Place> oldPlaceOptional = get(newPlace.getId());
        if (oldPlaceOptional.isPresent()) {
            Place oldPlace = oldPlaceOptional.get();
            oldPlace.setName(newPlace.getName());
            oldPlace.setDescription(newPlace.getDescription());
            oldPlace.setAddress(newPlace.getAddress());
            oldPlace.setPlaceTypes(newPlace.getPlaceTypes());
            add(oldPlace);
            return true;
        }
        return false;
    }

    @Override
    public final boolean delete(UUID id) {
        Optional<Place> object = get(id);
        if (object.isPresent()) {
            Place oldObject = object.get();
            addressService.delete(oldObject.getAddress().getId());
            oldObject.setActive(false);
            repository.save(oldObject);
            return true;
        }
        return false;
    }

    public Iterable<Place> getByPlaceTypes(Set<PlaceType> typeList) {
        PlaceRepository placeRepository = (PlaceRepository) repository;
        return placeRepository.getPlaceByActiveAndPlaceTypesIn(true, typeList);
    }

}
