package com.codecool.studer.repositories;

import com.codecool.studer.entities.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepositoryMethods<User, UUID> {

}
