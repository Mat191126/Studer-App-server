package com.company.studer.unit.services;

import com.company.studer.repositories.UserRepository;
import com.company.studer.services.LocationService;
import com.company.studer.services.UserService;

import java.util.UUID;

import static org.mockito.Mockito.mock;

public class UserServiceTest extends CrudServiceTest<UUID> {

    private final UserRepository repository = mock(UserRepository.class);

    @Override
    protected UserRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected UserService getService() {
        return new UserService(repository, mock(LocationService.class));
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }
}
