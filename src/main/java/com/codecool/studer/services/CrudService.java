package com.codecool.studer.services;

import com.codecool.studer.entities.BaseEntityMethods;
import com.codecool.studer.repositories.CrudRepositoryMethods;

import java.util.Optional;

public abstract class CrudService<T extends BaseEntityMethods<K>, K> implements BaseServiceMethods<T, K> {

    protected final CrudRepositoryMethods<T, K> repository;

    public CrudService(CrudRepositoryMethods<T, K> repository) {
        this.repository = repository;
    }

    @Override
    public final Iterable<T> getAll() {
        return repository.findAllByActive(true);
    }

    @Override
    public final Optional<T> get(K id) {
        return repository.findByIdAndActive(id, true);
    }

    @Override
    public final boolean add(T object) {
        repository.save(object);
        return repository.existsById(object.getId());
    }

}
