package com.company.studer.unit.services;

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
    private final FavouritePlace favouritePlace = mock(FavouritePlace.class);

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
    public void update_ReturnsTrue_WhenObjectIsUpdated() {
        //Arrange
        UUID uuid = getId();

        when(favouritePlace.getId()).thenReturn(uuid);
        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(favouritePlace));
        when(repository.save(favouritePlace)).thenReturn(favouritePlace);
        when(repository.existsById(favouritePlace.getId())).thenReturn(true);

        FavouritePlaceService favouritePlaceService = getService();
        //Act
        boolean actual = favouritePlaceService.update(favouritePlace);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(favouritePlace).setUser(favouritePlace.getUser()),
                () -> verify(favouritePlace).setPlace(favouritePlace.getPlace()),
                () -> verify(repository).save(favouritePlace),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        UUID uuid = getId();

        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(favouritePlace));
        when(repository.save(favouritePlace)).thenReturn(favouritePlace);

        FavouritePlaceService favouritePlaceService = getService();
        //Act
        boolean actual = favouritePlaceService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(favouritePlace).setActive(false),
                () -> verify(repository).save(favouritePlace),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void getByUserIdAndActive_ReturnsFavouritePlacesList_WhenUserIdGiven() {
        //Arrange
        List<FavouritePlace> expectedList = new ArrayList<>();
        UUID uuid = getId();

        when(repository.findByUserIdAndActive(uuid, true)).thenReturn(expectedList);
        FavouritePlaceService favouritePlaceService = getService();

        //Act
        List<FavouritePlace> actualList = (List<FavouritePlace>) favouritePlaceService.getByUserIdAndActive(uuid);

        //Assert
        assertAll(
                () -> verify(repository).findByUserIdAndActive(uuid, true),
                () -> assertSame(expectedList, actualList)
        );
    }

}
