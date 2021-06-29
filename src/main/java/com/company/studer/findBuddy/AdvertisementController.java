package com.company.studer.findBuddy;

import com.company.studer.findBuddy.entity.Advertisement;
import com.company.studer.findBuddy.service.AdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService userService) {
        this.advertisementService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private Iterable<Advertisement> getAll() {
        return advertisementService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Advertisement getById(@PathVariable UUID id) {
        return advertisementService.get(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    protected Advertisement addNewObject(@RequestBody Advertisement advertisement) {
        if (advertisementService.add(advertisement)) {
            return advertisement;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    protected void updateObject(@RequestBody Advertisement advertisement, @PathVariable UUID id) {
        if (!advertisement.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        if (!advertisementService.update(advertisement)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    protected void deleteObject(@PathVariable UUID id) {
        if (!advertisementService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
