package com.company.studer.repositories;

import com.company.studer.entities.Address;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends CrudRepositoryMethods<Address, UUID> {

}
