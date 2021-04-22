package com.company.studer.controllers;

import com.company.studer.entities.*;
import com.company.studer.services.AdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final CriteriaFactory criteriaFactory;

    public AdvertisementController(AdvertisementService userService, CriteriaFactory criteriaFactory) {
        this.advertisementService = userService;
        this.criteriaFactory = criteriaFactory;
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

    @GetMapping("/search/{phrases}")
    @ResponseStatus(HttpStatus.OK)
    private List<Phrase> searchForPrompt(@PathVariable List<String> phrases) {
        return advertisementService.getPromptsByPhrases(phrases);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    private List<Advertisement> searchForPrompt(@RequestParam String city) {
        return advertisementService.getAdvertisementsByActiveAndUserCity(city);
    }

    @GetMapping("/criteria")
    @ResponseStatus(HttpStatus.OK)
    private List<Criteria> getCriteriaList() {
        List<Criteria> criteria = new ArrayList<>();
        criteria.addAll(criteriaFactory.create(CriteriaType.AGE));
        criteria.addAll(criteriaFactory.create(CriteriaType.GENDER));
        criteria.addAll(criteriaFactory.create(CriteriaType.LANGUAGE));
        return criteria;
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
