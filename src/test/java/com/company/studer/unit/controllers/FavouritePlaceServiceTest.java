package com.company.studer.unit.controllers;

import com.company.studer.entities.FavouritePlace;
import com.company.studer.repositories.FavouritePlaceRepository;
import com.company.studer.services.FavouritePlaceService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FavouritePlaceServiceTest extends CrudServiceTest<UUID> {

    private final FavouritePlaceRepository repository = mock(FavouritePlaceRepository.class);

    protected FavouritePlaceRepository getRepositoryMock() {
        return repository;
    }

    protected FavouritePlaceService getService() {
        return new FavouritePlaceService(repository);
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }

    @Test
    public void getByUserIdAndActive_ReturnsFavouritePlacesList_WhenUserIdGiven() {
        //Arrange
        FavouritePlaceRepository favouritePlaceRepository = mock(FavouritePlaceRepository.class);

        List<FavouritePlace> expectedList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();

        when(favouritePlaceRepository.findByUserIdAndActive(eq(uuid), eq(true))).thenReturn(expectedList);
        FavouritePlaceService favouritePlaceService = new FavouritePlaceService(favouritePlaceRepository);

        //Act
        List<FavouritePlace> actualList = (List<FavouritePlace>) favouritePlaceService.getByUserIdAndActive(uuid);

        //Assert
        assertAll(
                () -> verify(favouritePlaceRepository, times(1)).findByUserIdAndActive(eq(uuid), eq(true)),
                () -> assertSame(expectedList, actualList)
        );
    }


    @Test
    public void getAll_ReturnsFavouritePlaceList() {
        //Arrange
        FavouritePlaceRepository favouritePlaceRepository = mock(FavouritePlaceRepository.class);

        List<FavouritePlace> expectedList = new ArrayList<>();

        when(favouritePlaceRepository.findAllByActive(eq(true))).thenReturn(expectedList);
        FavouritePlaceService favouritePlaceService = new FavouritePlaceService(favouritePlaceRepository);

        //Act
        List<FavouritePlace> actualList = (List<FavouritePlace>) favouritePlaceService.getAll();

        //Assert
        assertAll(
                () -> verify(favouritePlaceRepository, times(1)).findAllByActive(eq(true)),
                () -> assertSame(expectedList, actualList)
        );
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        FavouritePlaceRepository favouritePlaceRepository = mock(FavouritePlaceRepository.class);

        UUID uuid = UUID.randomUUID();

        FavouritePlace favouritePlace = mock(FavouritePlace.class);

        when(favouritePlaceRepository.findByIdAndActive(eq(uuid), eq(true))).thenReturn(Optional.of(favouritePlace));
        when(favouritePlaceRepository.save(favouritePlace)).thenReturn(favouritePlace);

        FavouritePlaceService favouritePlaceService = new FavouritePlaceService(favouritePlaceRepository);
        //Act
        boolean actual = favouritePlaceService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(favouritePlaceRepository, times(1)).findByIdAndActive(eq(uuid), eq(true)),
                () -> verify(favouritePlace, times(1)).setActive(eq(false)),
                () -> verify(favouritePlaceRepository, times(1)).save(favouritePlace),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void update_ReturnsTrue_WhenObjectIsUpdated() {
        //Arrange
        FavouritePlaceRepository favouritePlaceRepository = mock(FavouritePlaceRepository.class);

        UUID uuid = UUID.randomUUID();

        FavouritePlace favouritePlace = mock(FavouritePlace.class);

        when(favouritePlace.getId()).thenReturn(uuid);
        when(favouritePlaceRepository.findByIdAndActive(eq(uuid), eq(true))).thenReturn(Optional.of(favouritePlace));
        when(favouritePlaceRepository.save(favouritePlace)).thenReturn(favouritePlace);
        when(favouritePlaceRepository.existsById(eq(favouritePlace.getId()))).thenReturn(true);

        FavouritePlaceService favouritePlaceService = new FavouritePlaceService(favouritePlaceRepository);
        //Act
        boolean actual = favouritePlaceService.update(favouritePlace);

        //Assert
        assertAll(
                () -> verify(favouritePlaceRepository, times(1)).findByIdAndActive(eq(uuid), eq(true)),
                () -> verify(favouritePlace, times(1)).setUser(favouritePlace.getUser()),
                () -> verify(favouritePlace, times(1)).setPlace(favouritePlace.getPlace()),
                () -> verify(favouritePlaceRepository, times(1)).save(favouritePlace),
                () -> assertTrue(actual)
        );
    }

}
