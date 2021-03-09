package com.company.studer.unit.controllers;

import com.company.studer.entities.FavouritePlace;
import com.company.studer.entities.Place;
import com.company.studer.entities.User;
import com.company.studer.repositories.FavouritePlaceRepository;
import com.company.studer.services.FavouritePlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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
}
