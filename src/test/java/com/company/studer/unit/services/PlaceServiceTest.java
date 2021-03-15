package com.company.studer.unit.services;

import com.company.studer.repositories.PlaceRepository;
import com.company.studer.services.PlaceService;

import java.util.UUID;

import static org.mockito.Mockito.mock;

public class PlaceServiceTest extends CrudServiceTest<UUID> {

    private final PlaceRepository repository = mock(PlaceRepository.class);
    @Override
    protected PlaceRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected PlaceService getService() {
        return new PlaceService(repository, null);
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }
}
