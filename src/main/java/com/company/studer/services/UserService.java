package com.company.studer.services;

import com.company.studer.entities.User;
import com.company.studer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends CrudService<User, UUID> {

    private final UserRepository repository;
    private final LocationService locationService;

    @Autowired
    public UserService(UserRepository repository, LocationService locationService) {
        super(repository);
        this.repository = repository;
        this.locationService = locationService;
    }

    @Override
    public boolean update(User newUser) {
        Optional<User> oldUserOptional = get(newUser.getId());
        if (oldUserOptional.isPresent()) {
            User oldUser = oldUserOptional.get();
            oldUser.setName(newUser.getName());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPassword(newUser.getPassword());
            oldUser.setBirthDate(newUser.getBirthDate());
            oldUser.setLocation(newUser.getLocation());
            oldUser.setCity(newUser.getCity());
            oldUser.setUniversity(newUser.getUniversity());
            oldUser.setGender(newUser.getGender());
            oldUser.setPhoto(newUser.getPhoto());
            add(oldUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
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

    public LocalDate getMinimumUserAge() {
        return repository.findFirstByActiveOrderByBirthDateDesc(true).getBirthDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate getMaximumUserAge() {
        return repository.findFirstByActiveOrderByBirthDateAsc(true).getBirthDate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
