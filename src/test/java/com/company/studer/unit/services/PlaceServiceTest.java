package com.company.studer.unit.services;

import com.company.studer.map.entity.Address;
import com.company.studer.map.entity.Place;
import com.company.studer.map.entity.PlaceType;
import com.company.studer.map.repository.PlaceRepository;
import com.company.studer.map.service.AddressService;
import com.company.studer.map.service.PlaceService;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlaceServiceTest extends CrudServiceTest<UUID> {

    private final PlaceRepository repository = mock(PlaceRepository.class);
    private final Place place = mock(Place.class);
    private final AddressService addressService = mock(AddressService.class);

    @Override
    protected PlaceRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected PlaceService getService() {
        return new PlaceService(repository, addressService);
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
        when(address.getId()).thenReturn(uuid);
        when(repository.save(place)).thenReturn(place);

        PlaceService placeService = getService();
        //Act
        boolean actual = placeService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(addressService).delete(uuid),
                () -> verify(place).setActive(false),
                () -> verify(repository).save(place),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void getByPlaceTypes_ReturnsPlacesList_WhenTypesGiven() {
        //Arrange
        List<Place> expectedList = new ArrayList<>();
        Set<PlaceType> placeTypes = new HashSet<>();

        when(repository.findPlaceByActiveAndPlaceTypesIn(true, placeTypes)).thenReturn(expectedList);
        PlaceService placeService = getService();

        //Act
        List<Place> actualList = (List<Place>) placeService.getByPlaceTypes(placeTypes);

        //Assert
        assertAll(
                () -> verify(repository).findPlaceByActiveAndPlaceTypesIn(true, placeTypes),
                () -> assertSame(expectedList, actualList)
        );
    }
}
