package com.company.studer.repositories;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SelectableByUserIdAndActive<T, V> {

    Iterable<T> getByUserIdAndActive(V id, boolean active);
}
