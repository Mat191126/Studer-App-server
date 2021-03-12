package com.company.studer.unit.controllers;

import com.company.studer.entities.BaseEntityMethods;
import com.company.studer.repositories.CrudRepositoryMethods;
import com.company.studer.services.CrudService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public abstract class CrudServiceTest <V>{

    @Test
    public void get_ReturnsFavouritePlaceObject_WhenIdGiven() {
        //Arrange
        CrudRepositoryMethods<? extends BaseEntityMethods<V>, V> repository = getRepositoryMock();

        BaseEntityMethods<V> entity = mock(BaseEntityMethods.class);
        Optional<? extends BaseEntityMethods<V>> expected = Optional.of(entity);
        V id = getId();

        doReturn(Optional.of(entity)).when(repository).findByIdAndActive(id, true);
        CrudService<? extends BaseEntityMethods<V>, V> service = getService();

        //Act
        Optional<? extends BaseEntityMethods<V>> actual = service.get(id);

        //Assert
        assertAll(
                () -> verify(repository, times(1)).findByIdAndActive(eq(id), eq(true)),
                () -> assertEquals(expected, actual)
        );
    }

    protected abstract CrudRepositoryMethods<? extends BaseEntityMethods<V>, V> getRepositoryMock();

    protected abstract CrudService<? extends BaseEntityMethods<V>, V> getService();

    protected abstract V getId();
}
