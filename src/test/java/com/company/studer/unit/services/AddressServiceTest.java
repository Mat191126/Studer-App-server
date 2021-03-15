package com.company.studer.unit.services;

import com.company.studer.repositories.AddressRepository;
import com.company.studer.services.AddressService;
import com.company.studer.services.LocationService;

import java.util.UUID;

import static org.mockito.Mockito.mock;

public class AddressServiceTest extends CrudServiceTest<UUID> {

    private final AddressRepository repository = mock(AddressRepository.class);

    @Override
    protected AddressRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected AddressService getService() {
        return new AddressService(repository, mock(LocationService.class));
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }
}
