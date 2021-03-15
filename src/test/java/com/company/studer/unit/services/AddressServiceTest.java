package com.company.studer.unit.services;

import com.company.studer.entities.Address;
import com.company.studer.entities.FavouritePlace;
import com.company.studer.entities.Location;
import com.company.studer.repositories.AddressRepository;
import com.company.studer.repositories.FavouritePlaceRepository;
import com.company.studer.services.AddressService;
import com.company.studer.services.FavouritePlaceService;
import com.company.studer.services.LocationService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AddressServiceTest extends CrudServiceTest<UUID> {

    private final AddressRepository repository = mock(AddressRepository.class);
    private final Address address = mock(Address.class);

    @Override
    protected AddressRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected AddressService getService() {
        return new AddressService(repository, mock(LocationService.class));
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        UUID uuid = getId();
        Location location = mock(Location.class);
        LocationService locationService = mock(LocationService.class);

        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(address));
        when(locationService.delete(uuid)).thenReturn(true);
        when(address.getLocation()).thenReturn(location);
        when(repository.save(address)).thenReturn(address);

        AddressService addressService = getService();
        //Act
        boolean actual = addressService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(repository, times(1)).findByIdAndActive(eq(uuid), eq(true)),
                () -> verify(address, times(1)).setActive(eq(false)),
                () -> verify(repository, times(1)).save(address),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void update_ReturnsTrue_WhenObjectIsUpdated() {
        //Arrange
        UUID uuid = getId();

        when(address.getId()).thenReturn(uuid);
        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(address));
        when(repository.save(address)).thenReturn(address);
        when(repository.existsById(address.getId())).thenReturn(true);

        AddressService favouritePlaceService = getService();
        //Act
        boolean actual = favouritePlaceService.update(address);

        //Assert
        assertAll(
                () -> verify(repository, times(1)).findByIdAndActive(uuid, true),
                () -> verify(address, times(1)).setStreet(address.getStreet()),
                () -> verify(address, times(1)).setStreetNumber(address.getStreetNumber()),
                () -> verify(address, times(1)).setTown(address.getTown()),
                () -> verify(address, times(1)).setZipCode(address.getZipCode()),
                () -> verify(address, times(1)).setLocation(address.getLocation()),
                () -> verify(repository, times(1)).save(address),
                () -> assertTrue(actual)
        );
    }
}
