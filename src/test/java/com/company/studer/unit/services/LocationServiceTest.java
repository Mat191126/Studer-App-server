package com.company.studer.unit.services;

import com.company.studer.repositories.LocationRepository;
import com.company.studer.services.LocationService;

import java.util.UUID;

import static org.mockito.Mockito.mock;

public class LocationServiceTest extends CrudServiceTest<UUID> {

    private final LocationRepository repository = mock(LocationRepository.class);

    @Override
    protected LocationRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected LocationService getService() {
        return new LocationService(repository);
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }
}
