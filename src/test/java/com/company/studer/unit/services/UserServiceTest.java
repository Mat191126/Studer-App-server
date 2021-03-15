package com.company.studer.unit.services;

import com.company.studer.entities.Location;
import com.company.studer.entities.User;
import com.company.studer.repositories.UserRepository;
import com.company.studer.services.AddressService;
import com.company.studer.services.LocationService;
import com.company.studer.services.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class UserServiceTest extends CrudServiceTest<UUID> {

    private final UserRepository repository = mock(UserRepository.class);
    private final User user = mock(User.class);
    private final LocationService locationService = mock(LocationService.class);

    @Override
    protected UserRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected UserService getService() {
        return new UserService(repository, locationService);
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }

    @Test
    public void update_ReturnsTrue_WhenObjectIsUpdated() {
        //Arrange
        UUID uuid = getId();

        when(user.getId()).thenReturn(uuid);
        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);
        when(repository.existsById(user.getId())).thenReturn(true);

        UserService userService = getService();
        //Act
        boolean actual = userService.update(user);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(user).setFirstName(user.getFirstName()),
                () -> verify(user).setLastName(user.getLastName()),
                () -> verify(user).setEmail(user.getEmail()),
                () -> verify(user).setPassword(user.getPassword()),
                () -> verify(user).setUserRole(user.getUserRole()),
                () -> verify(user).setLocation(user.getLocation()),
                () -> verify(repository).save(user),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        UUID uuid = getId();
        Location location = mock(Location.class);

        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(user));
        when(user.getLocation()).thenReturn(location);
        when(location.getId()).thenReturn(uuid);
        when(repository.save(user)).thenReturn(user);

        UserService userService = getService();
        //Act
        boolean actual = userService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(locationService).delete(uuid),
                () -> verify(user).setActive(false),
                () -> verify(repository).save(user),
                () -> assertTrue(actual)
        );
    }
}
