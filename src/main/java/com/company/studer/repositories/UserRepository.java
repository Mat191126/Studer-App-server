package com.company.studer.repositories;

import com.company.studer.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepositoryMethods<User, UUID> {

    User findFirstByActiveOrderByAgeDesc(boolean active);

    User findFirstByActiveOrderByAgeAsc(boolean active);
}
