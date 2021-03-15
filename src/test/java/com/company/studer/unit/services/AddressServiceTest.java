package com.company.studer.unit.services;

import com.company.studer.entities.Address;
import com.company.studer.entities.Location;
import com.company.studer.repositories.AddressRepository;
import com.company.studer.services.AddressService;
import com.company.studer.services.LocationService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AddressServiceTest extends CrudServiceTest<UUID> {

    private final AddressRepository repository = mock(AddressRepository.class);
    private final Address address = mock(Address.class);
    private final LocationService locationService = mock(LocationService.class);

    @Override
    protected AddressRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected AddressService getService() {
        return new AddressService(repository, locationService);
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
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
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(address).setStreet(address.getStreet()),
                () -> verify(address).setStreetNumber(address.getStreetNumber()),
                () -> verify(address).setTown(address.getTown()),
                () -> verify(address).setZipCode(address.getZipCode()),
                () -> verify(address).setLocation(address.getLocation()),
                () -> verify(repository).save(address),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        UUID uuid = getId();
        Location location = mock(Location.class);

        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(address));
        when(address.getLocation()).thenReturn(location);
        when(location.getId()).thenReturn(uuid);
        when(repository.save(address)).thenReturn(address);

        AddressService addressService = getService();
        //Act
        boolean actual = addressService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(locationService).delete(uuid),
                () -> verify(address).setActive(false),
                () -> verify(repository).save(address),
                () -> assertTrue(actual)
        );
    }
}
