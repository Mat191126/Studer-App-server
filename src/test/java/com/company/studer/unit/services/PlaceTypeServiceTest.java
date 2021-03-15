package com.company.studer.unit.services;

import com.company.studer.repositories.PlaceTypeRepository;
import com.company.studer.services.PlaceTypeService;

import static org.mockito.Mockito.mock;

public class PlaceTypeServiceTest extends CrudServiceTest<Long> {

    private final PlaceTypeRepository repository = mock(PlaceTypeRepository.class);

    @Override
    protected PlaceTypeRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected PlaceTypeService getService() {
        return new PlaceTypeService(repository);
    }

    @Override
    protected Long getId() {
        return (long) (1000L + (Math.random() * (9999L - 1L)));
    }
}
