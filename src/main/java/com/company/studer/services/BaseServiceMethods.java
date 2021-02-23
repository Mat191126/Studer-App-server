package com.company.studer.services;

import java.util.Optional;

public interface BaseServiceMethods<T, K> {

    Iterable<T> getAll();

    Optional<T> get(K id);

    boolean add(T object);

    boolean update(T object);

    boolean delete(K id);
}
