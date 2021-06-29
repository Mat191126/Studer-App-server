package com.company.studer.profile;

import com.company.studer.profile.entity.FavouritePlace;
import com.company.studer.profile.service.FavouritePlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    protected FavouritePlace addNewObject(@RequestBody FavouritePlace place) {
        if (favouritePlaceService.add(place)) {
            return place;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    protected void deleteObject(@PathVariable UUID id) {
        if (!favouritePlaceService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
