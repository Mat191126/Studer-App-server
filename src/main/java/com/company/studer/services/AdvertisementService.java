package com.company.studer.services;

import com.company.studer.entities.Advertisement;
import com.company.studer.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdvertisementService extends CrudService<Advertisement, UUID> {

    public AdvertisementService(AdvertisementRepository repository) {
        super(repository);
    }

    @Override
    public boolean update(Advertisement newAdvertisement) {
        Optional<Advertisement> oldAdvertisementOptional = get(newAdvertisement.getId());
        if (oldAdvertisementOptional.isPresent()) {
            Advertisement oldAdvertisement = oldAdvertisementOptional.get();
            oldAdvertisement.setUser(newAdvertisement.getUser());
            oldAdvertisement.setTitle(newAdvertisement.getTitle());
            oldAdvertisement.setDescription(newAdvertisement.getDescription());
            add(oldAdvertisement);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        Optional<Advertisement> object = get(id);
        if (object.isPresent()) {
            Advertisement oldObject = object.get();
            oldObject.setActive(false);
            repository.save(oldObject);
            return true;
        }
        return false;
    }
}
