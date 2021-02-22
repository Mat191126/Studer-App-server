package com.codecool.studer.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudRepositoryBehaviour<T, V> extends CrudRepository<T, V> {

    Iterable<T> findAllByActive(boolean active);

    Optional<T> findByIdAndActive(V id, boolean active);

}
