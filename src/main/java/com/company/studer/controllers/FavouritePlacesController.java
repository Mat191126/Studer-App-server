package com.company.studer.controllers;

import com.company.studer.entities.FavouritePlace;
import com.company.studer.services.FavouritePlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/favourite_places")
public class FavouritePlacesController {

    private final FavouritePlaceService favouritePlaceService;

    public FavouritePlacesController(FavouritePlaceService favouritePlaceService) {
        this.favouritePlaceService = favouritePlaceService;
    }

    @GetMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    private Iterable<FavouritePlace> getByUserId(@PathVariable UUID user_id) {
        return favouritePlaceService.getByUserIdAndActive(user_id);
    }
}
