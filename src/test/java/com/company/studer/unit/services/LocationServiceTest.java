package com.company.studer.unit.services;

import com.company.studer.map.entity.Location;
import com.company.studer.map.repository.LocationRepository;
import com.company.studer.map.service.LocationService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class LocationServiceTest extends CrudServiceTest<UUID> {

    private final LocationRepository repository = mock(LocationRepository.class);
    private final Location location = mock(Location.class);

    @Override
    protected LocationRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected LocationService getService() {
        return new LocationService(repository);
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }

    @Test
    public void update_ReturnsTrue_WhenObjectIsUpdated() {
        //Arrange
        UUID uuid = getId();

        when(location.getId()).thenReturn(uuid);
        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(location));
        when(repository.save(location)).thenReturn(location);
        when(repository.existsById(location.getId())).thenReturn(true);

        LocationService locationService = getService();
        //Act
        boolean actual = locationService.update(location);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(location).setPoint(location.getPoint()),
                () -> verify(repository).save(location),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        UUID uuid = getId();

        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(location));
        when(repository.save(location)).thenReturn(location);

        LocationService locationService = getService();
        //Act
        boolean actual = locationService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(location).setActive(false),
                () -> verify(repository).save(location),
                () -> assertTrue(actual)
        );
    }
}
