package com.company.studer.unit.services;

import com.company.studer.entities.Location;
import com.company.studer.repositories.LocationRepository;
import com.company.studer.services.FavouritePlaceService;
import com.company.studer.services.LocationService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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

//    @Test
//    public void update_ReturnsTrue_WhenObjectIsUpdated() {
//        //Arrange
//        UUID uuid = getId();
//
//        when(location.getId()).thenReturn(uuid);
//        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(location));
//        when(repository.save(location)).thenReturn(location);
//        when(repository.existsById(location.getId())).thenReturn(true);
//
//        FavouritePlaceService favouritePlaceService = getService();
//        //Act
//        boolean actual = favouritePlaceService.update(location);
//
//        //Assert
//        assertAll(
//                () -> verify(repository, times(1)).findByIdAndActive(uuid, true),
//                () -> verify(favouritePlace, times(1)).setUser(favouritePlace.getUser()),
//                () -> verify(favouritePlace, times(1)).setPlace(favouritePlace.getPlace()),
//                () -> verify(repository, times(1)).save(favouritePlace),
//                () -> assertTrue(actual)
//        );
//    }
}
