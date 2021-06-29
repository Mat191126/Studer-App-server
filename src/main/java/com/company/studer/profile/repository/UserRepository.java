package com.company.studer.profile.repository;

import com.company.studer.common.repository.CrudRepositoryMethods;
import com.company.studer.profile.entity.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepositoryMethods<User, UUID> {

    User findFirstByActiveOrderByBirthDateDesc(boolean active);

    User findFirstByActiveOrderByBirthDateAsc(boolean active);
}
