package com.company.studer.services;

import com.company.studer.entities.Address;
import com.company.studer.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService extends CrudService<Address, UUID, AddressRepository> {

    private final LocationService locationService;

    @Autowired
    public AddressService(AddressRepository repository, LocationService locationService) {
        super(repository);
        this.locationService = locationService;
    }

    @Override
    public boolean update(Address newAddress) {
        Optional<Address> oldAddressOptional = get(newAddress.getId());
        if (oldAddressOptional.isPresent()) {
            Address oldAddress = oldAddressOptional.get();
            oldAddress.setStreet(newAddress.getStreet());
            oldAddress.setStreetNumber(newAddress.getStreetNumber());
            oldAddress.setTown(newAddress.getTown());
            oldAddress.setZipCode(newAddress.getZipCode());
            oldAddress.setLocation(newAddress.getLocation());
            add(oldAddress);
            return true;
        }
        return false;
    }

    @Override
    public final boolean delete(UUID id) {
        Optional<Address> object = get(id);
        if (object.isPresent()) {
            Address oldObject = object.get();
            locationService.delete(oldObject.getLocation().getId());
            oldObject.setActive(false);
            repository.save(oldObject);
            return true;
        }
        return false;
    }

}
