package com.company.studer.controllers;

import com.company.studer.entities.Place;
import com.company.studer.entities.PlaceType;
import com.company.studer.services.PlaceService;
import com.company.studer.services.PlaceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;
    private final PlaceTypeService placeTypeService;

    public PlaceController(PlaceService userService, PlaceTypeService placeTypeService) {
        this.placeService = userService;
        this.placeTypeService = placeTypeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private Iterable<Place> getAll() {
        return placeService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Place getById(@PathVariable UUID id) {
        return placeService.get(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("/type/{type_list}")
    @ResponseStatus(HttpStatus.OK)
    private Iterable<Place> getByPlaceTypes(@PathVariable Set<String> type_list) {
        Set<PlaceType> placeTypes = new HashSet<>();
        for (String placeTypeString : type_list) {
            placeTypes.add(placeTypeService.getByType(placeTypeString).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ));
        }
        return placeService.getByPlaceTypes(placeTypes);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    protected Place addNewObject(@RequestBody Place place) {
        if (placeService.add(place)) {
            return place;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    protected void updateObject(@RequestBody Place place, @PathVariable UUID id) {
        if (!place.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (!placeService.update(place)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    protected void deleteObject(@PathVariable UUID id) {
        if (!placeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
