package com.company.studer.map.service;

import com.company.studer.common.service.CrudService;
import com.company.studer.map.entity.Location;
import com.company.studer.map.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService extends CrudService<Location, UUID> {

    @Autowired
    public LocationService(LocationRepository repository) {
        super(repository);
    }

    @Override
    public boolean update(Location newLocation) {
        Optional<Location> oldAddressOptional = get(newLocation.getId());
        if (oldAddressOptional.isPresent()) {
            Location oldLocation = oldAddressOptional.get();
            oldLocation.setPoint(newLocation.getPoint());
            add(oldLocation);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        Optional<Location> object = get(id);
        if (object.isPresent()) {
            Location oldObject = object.get();
            oldObject.setActive(false);
            repository.save(oldObject);
            return true;
        }
        return false;
    }
}
