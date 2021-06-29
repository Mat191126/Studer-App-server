package com.company.studer.map.service;

import com.company.studer.common.service.CrudService;
import com.company.studer.map.entity.Place;
import com.company.studer.map.entity.PlaceType;
import com.company.studer.map.repository.PlaceRepository;
import com.company.studer.map.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class PlaceService extends CrudService<Place, UUID> {

    private final PlaceRepository repository;
    private final AddressService addressService;

    @Autowired
    public PlaceService(PlaceRepository repository, AddressService addressService) {
        super(repository);
        this.repository = repository;
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
    public boolean delete(UUID id) {
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
        return repository.findPlaceByActiveAndPlaceTypesIn(true, typeList);
    }

    public Iterable<Place> getByPlaceTypesInRadius(Set<PlaceType> typeList, String centerLatitude,
                                                   String centerLongitude, int radius) {

        String mapCenter = String.format("POINT (%s %s)", centerLatitude, centerLongitude);
        return repository.findPlaceByActiveAndTypesInRadius(mapCenter, radius, true, typeList);
    }

}
