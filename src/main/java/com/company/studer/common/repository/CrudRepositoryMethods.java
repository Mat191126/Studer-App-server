package com.company.studer.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CrudRepositoryMethods<T, V> extends CrudRepository<T, V> {

    Iterable<T> findAllByActive(boolean active);

    Optional<T> findByIdAndActive(V id, boolean active);

}
