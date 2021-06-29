package com.company.studer.unit.services;

import com.company.studer.findBuddy.entity.Advertisement;
import com.company.studer.findBuddy.entity.CriteriaFactory;
import com.company.studer.map.entity.Location;
import com.company.studer.findBuddy.repository.AdvertisementRepository;
import com.company.studer.findBuddy.service.AdvertisementService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AdvertisementServiceTest extends CrudServiceTest<UUID> {

    private final AdvertisementRepository repository = mock(AdvertisementRepository.class);
    private final Advertisement advertisement = mock(Advertisement.class);
    private final CriteriaFactory criteriaFactory = mock(CriteriaFactory.class);

    @Override
    protected AdvertisementRepository getRepositoryMock() {
        return repository;
    }

    @Override
    protected AdvertisementService getService() {
        return new AdvertisementService(repository, criteriaFactory);
    }

    @Override
    protected UUID getId() {
        return UUID.randomUUID();
    }

    @Test
    public void update_ReturnsTrue_WhenObjectIsUpdated() {
        //Arrange
        UUID uuid = getId();

        when(advertisement.getId()).thenReturn(uuid);
        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(advertisement));
        when(repository.save(advertisement)).thenReturn(advertisement);
        when(repository.existsById(advertisement.getId())).thenReturn(true);

        AdvertisementService advertisementService = getService();
        //Act
        boolean actual = advertisementService.update(advertisement);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(advertisement).setUser(advertisement.getUser()),
                () -> verify(advertisement).setTitle(advertisement.getTitle()),
                () -> verify(advertisement).setDescription(advertisement.getDescription()),
                () -> verify(repository).save(advertisement),
                () -> assertTrue(actual)
        );
    }

    @Test
    public void delete_ReturnsTrue_WhenObjectIsDeleted() {
        //Arrange
        UUID uuid = getId();
        Location location = mock(Location.class);

        when(repository.findByIdAndActive(uuid, true)).thenReturn(Optional.of(advertisement));
        when(location.getId()).thenReturn(uuid);
        when(repository.save(advertisement)).thenReturn(advertisement);

        AdvertisementService addressService = getService();
        //Act
        boolean actual = addressService.delete(uuid);

        //Assert
        assertAll(
                () -> verify(repository).findByIdAndActive(uuid, true),
                () -> verify(advertisement).setActive(false),
                () -> verify(repository).save(advertisement),
                () -> assertTrue(actual)
        );
    }
}
