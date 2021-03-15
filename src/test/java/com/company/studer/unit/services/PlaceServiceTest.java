package com.company.studer.unit.services;

import com.company.studer.entities.Address;
import com.company.studer.entities.Place;
import com.company.studer.repositories.PlaceRepository;
import com.company.studer.services.AddressService;
import com.company.studer.services.LocationService;
import com.company.studer.services.PlaceService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class PlaceServiceTest extends CrudServiceTest<UUID> {

    private final PlaceRepository repository = mock(PlaceRepository.class);
    private final Place place = mock(Place.class);

    @Override
    protected PlaceRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected PlaceService getService() {
        return new PlaceService(repository, mock(AddressService.class));
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }

    @Test
    public void update_ReturnsTrue_WhenObjectIsUpdated() {
        //Arrange
        UUID uuid = getId();

        when(place.getId()).thenReturn(uuid);
        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(place));
        when(repository.save(place)).thenReturn(place);
        when(repository.existsById(place.getId())).thenReturn(true);

        PlaceService placeService = getService();
        //Act
        boolean actual = placeService.update(place);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(place).setName(place.getName()),
                () -> verify(place).setDescription(place.getDescription()),
                () -> verify(place).setAddress(place.getAddress()),
                () -> verify(place).setPlaceTypes(place.getPlaceTypes()),
                () -> verify(repository).save(place),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        UUID uuid = getId();
        Address address = mock(Address.class);

        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(place));
        when(place.getAddress()).thenReturn(address);
        when(repository.save(place)).thenReturn(place);

        PlaceService placeService = getService();
        //Act
        boolean actual = placeService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(place).setActive(false),
                () -> verify(repository).save(place),
                () -> assertTrue(actual)
        );
    }
}
