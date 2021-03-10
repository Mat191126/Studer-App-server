package com.company.studer.unit.controllers;

import com.company.studer.entities.FavouritePlace;
import com.company.studer.repositories.FavouritePlaceRepository;
import com.company.studer.services.FavouritePlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class FavouritePlaceServiceTest {

    @Test
    public void getByUserIdAndActive_ReturnsFavouritePlacesList_WhenUserIdGiven() {
        //Arrange
        FavouritePlaceRepository favouritePlaceRepository = mock(FavouritePlaceRepository.class);

        List<FavouritePlace> expectedList = new ArrayList<>();
        UUID uuid = UUID.randomUUID();

        when(favouritePlaceRepository.getByUserIdAndActive(eq(uuid), eq(true))).thenReturn(expectedList);
        FavouritePlaceService favouritePlaceService = new FavouritePlaceService(favouritePlaceRepository);

        //Act
        List<FavouritePlace> actualList = (List<FavouritePlace>) favouritePlaceService.getByUserIdAndActive(uuid);

        //Assert
        assertAll(
                () -> verify(favouritePlaceRepository, times(1)).getByUserIdAndActive(eq(uuid), eq(true)),
                () -> assertSame(expectedList, actualList)
        );
    }

    @Test
    public void get_ReturnsFavouritePlaceObject_WhenIdGiven() {
        //Arrange
        FavouritePlaceRepository favouritePlaceRepository = mock(FavouritePlaceRepository.class);

        FavouritePlace favouritePlace = mock(FavouritePlace.class);
        Optional<FavouritePlace> expected = Optional.of(favouritePlace);
        UUID uuid = UUID.randomUUID();

        when(favouritePlaceRepository.findByIdAndActive(eq(uuid), eq(true))).thenReturn(Optional.of(favouritePlace));
        FavouritePlaceService favouritePlaceService = new FavouritePlaceService(favouritePlaceRepository);

        //Act
        Optional<FavouritePlace> actual = favouritePlaceService.get(uuid);

        //Assert
        assertAll(
                () -> verify(favouritePlaceRepository, times(1)).findByIdAndActive(eq(uuid), eq(true)),
                () -> assertEquals(expected, actual)
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

}
