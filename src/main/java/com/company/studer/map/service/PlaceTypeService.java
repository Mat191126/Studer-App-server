package com.company.studer.map.service;

import com.company.studer.common.service.CrudService;
import com.company.studer.map.entity.PlaceType;
import com.company.studer.map.repository.PlaceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaceTypeService extends CrudService<PlaceType, Long> {

    private final PlaceTypeRepository repository;

    @Autowired
    public PlaceTypeService(PlaceTypeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public boolean update(PlaceType newPlaceType) {
        Optional<PlaceType> oldPlaceTypeOptional = get(newPlaceType.getId());
        if (oldPlaceTypeOptional.isPresent()) {
            PlaceType oldPlaceType = oldPlaceTypeOptional.get();
            oldPlaceType.setType(newPlaceType.getType());
            add(oldPlaceType);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Optional<PlaceType> object = get(id);
        if (object.isPresent()) {
            PlaceType oldObject = object.get();
            oldObject.setActive(false);
            repository.save(oldObject);
            return true;
        }
        return false;
    }

    public Optional<PlaceType> getByType(String type) {
        return repository.findPlaceTypeByType(type);
    }
}
