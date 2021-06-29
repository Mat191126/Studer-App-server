package com.company.studer.map;

import com.company.studer.map.entity.Place;
import com.company.studer.map.entity.PlaceType;
import com.company.studer.map.service.PlaceService;
import com.company.studer.map.service.PlaceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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

    @GetMapping("/type")
    @ResponseStatus(HttpStatus.OK)
    private Iterable<PlaceType> getById() {
        return placeTypeService.getAll();
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

    @GetMapping("/type/radius/{type_list}")
    @ResponseStatus(HttpStatus.OK)
    private Iterable<Place> getByPlaceTypesInRadius(@PathVariable Set<String> type_list,
                                                    @RequestParam String centerLatitude,
                                                    @RequestParam String centerLongitude,
                                                    @RequestParam String radius) {
        Set<PlaceType> placeTypes = new HashSet<>();
        for (String placeTypeString : type_list) {
            placeTypes.add(placeTypeService.getByType(placeTypeString).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ));
        }
        int radiusInteger = Integer.parseInt(radius);
        return placeService.getByPlaceTypesInRadius(placeTypes, centerLatitude, centerLongitude, radiusInteger);
    }

    @GetMapping("/type/radius")
    @ResponseStatus(HttpStatus.OK)
    private Iterable<Place> getWithoutPlaceTypesInRadius() {
        return Collections.emptyList();
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