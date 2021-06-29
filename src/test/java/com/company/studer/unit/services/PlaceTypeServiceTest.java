package com.company.studer.unit.services;

import com.company.studer.map.entity.PlaceType;
import com.company.studer.map.repository.PlaceTypeRepository;
import com.company.studer.map.service.PlaceTypeService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlaceTypeServiceTest extends CrudServiceTest<Long> {

    private final PlaceTypeRepository repository = mock(PlaceTypeRepository.class);
    private final PlaceType placeType = mock(PlaceType.class);

    @Override
    protected PlaceTypeRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected PlaceTypeService getService() {
        return new PlaceTypeService(repository);
    }

    @Override
    protected Long getId() {
        return (long) (1000L + (Math.random() * (9999L - 1L)));
    }

    @Test
    public void update_ReturnsTrue_WhenObjectIsUpdated() {
        //Arrange
        Long id = getId();

        when(placeType.getId()).thenReturn(id);
        when(repository.findByIdAndActive(id, true)).thenReturn(Optional.of(placeType));
        when(repository.save(placeType)).thenReturn(placeType);
        when(repository.existsById(placeType.getId())).thenReturn(true);

        PlaceTypeService placeTypeService = getService();
        //Act
        boolean actual = placeTypeService.update(placeType);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(id, true),
                () -> verify(placeType).setType(placeType.getType()),
                () -> verify(repository).save(placeType),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        Long id = getId();

        when(repository.findByIdAndActive(id, true)).thenReturn(Optional.of(placeType));
        when(repository.save(placeType)).thenReturn(placeType);

        PlaceTypeService placeTypeService = getService();
        //Act
        boolean actual = placeTypeService.delete(id);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(id, true),
                () -> verify(placeType).setActive(false),
                () -> verify(repository).save(placeType),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void getByType_ReturnsPlaceTypeObject_WhenTypeGiven() {
        //Arrange
        Optional<PlaceType> expected = Optional.of(placeType);

        when(repository.findPlaceTypeByType(anyString())).thenReturn(Optional.of(placeType));
        PlaceTypeService placeTypeService = getService();

        //Act
        Optional<PlaceType> actual = placeTypeService.getByType(anyString());

        //Assert
        assertAll(
                () -> verify(repository).findPlaceTypeByType(anyString()),
                () -> assertEquals(expected, actual)
        );
    }
}
