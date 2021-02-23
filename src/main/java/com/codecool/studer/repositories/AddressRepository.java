package com.codecool.studer.repositories;

import com.codecool.studer.entities.Address;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends CrudRepositoryBehaviour<Address, UUID> {

}
