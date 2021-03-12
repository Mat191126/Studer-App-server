package com.company.studer.unit.controllers;

import com.company.studer.entities.Place;
import com.company.studer.repositories.CrudRepositoryMethods;
import com.company.studer.repositories.PlaceRepository;
import com.company.studer.services.CrudService;
import com.company.studer.services.PlaceService;

import java.util.UUID;

import static org.mockito.Mockito.mock;

public class PlaceServiceTest extends CrudServiceTest<UUID> {

    private final PlaceRepository repository = mock(PlaceRepository.class);
    @Override
    protected CrudRepositoryMethods<Place, UUID> getRepositoryMock() {
        return repository;
    }

    @Override
    protected CrudService<Place, UUID> getService() {
        return new PlaceService(repository, null);
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }
}
