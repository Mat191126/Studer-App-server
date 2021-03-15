package com.company.studer.unit.services;

import com.company.studer.entities.BaseEntityMethods;
import com.company.studer.repositories.CrudRepositoryMethods;
import com.company.studer.services.CrudService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public abstract class CrudServiceTest <V>{

    protected abstract CrudRepositoryMethods<? extends BaseEntityMethods<V>, V> getRepositoryMock();

    protected abstract CrudService<? extends BaseEntityMethods<V>, V> getService();

    protected abstract V getId();

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
                () -> verify(repository).findByIdAndActive(eq(id), eq(true)),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    public void getAll_ReturnsFavouritePlaceList() {
        //Arrange
        CrudRepositoryMethods<? extends BaseEntityMethods<V>, V> repository = getRepositoryMock();

        List<BaseEntityMethods<V>> expectedList = new ArrayList<>();

        doReturn(new ArrayList<BaseEntityMethods<V>>()).when(repository).findAllByActive(true);
        CrudService<? extends BaseEntityMethods<V>, V> service = getService();
        //Act
        List<BaseEntityMethods<V>> actualList = (List<BaseEntityMethods<V>>) service.getAll();

        //Assert
        assertAll(
                () -> verify(repository).findAllByActive(eq(true)),
                () -> assertEquals(expectedList, actualList)
        );
    }
}
