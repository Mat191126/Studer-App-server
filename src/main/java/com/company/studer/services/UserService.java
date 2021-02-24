package com.company.studer.services;

import com.company.studer.entities.User;
import com.company.studer.repositories.CrudRepositoryMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends CrudService<User, UUID> {

    private final LocationService locationService;

    @Autowired
    public UserService(CrudRepositoryMethods<User, UUID> repository, LocationService locationService) {
        super(repository);
        this.locationService = locationService;
    }

    @Override
    public boolean update(User newUser) {
        Optional<User> oldUserOptional = get(newUser.getId());
        if (oldUserOptional.isPresent()) {
            User oldUser = oldUserOptional.get();
            oldUser.setFirstName(newUser.getFirstName());
            oldUser.setLastName(newUser.getLastName());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setUserRole(newUser.getUserRole());
            oldUser.setLocation(newUser.getLocation());
            add(oldUser);
            return true;
        }
        return false;
    }

    @Override
    public final boolean delete(UUID id) {
        Optional<User> object = get(id);
        if (object.isPresent()) {
            User oldObject = object.get();
            locationService.delete(oldObject.getLocation().getId());
            oldObject.setActive(false);
            repository.save(oldObject);
            return true;
        }
        return false;
    }

}
