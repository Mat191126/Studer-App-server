package com.company.studer.repositories;

import com.company.studer.entities.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepositoryMethods<User, UUID> {

    User findFirstByActiveOrderByBirthDateDesc(boolean active);

    User findFirstByActiveOrderByBirthDateAsc(boolean active);
}
