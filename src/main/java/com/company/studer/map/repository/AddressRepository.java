package com.company.studer.map.repository;

import com.company.studer.common.repository.CrudRepositoryMethods;
import com.company.studer.map.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends CrudRepositoryMethods<Address, UUID> {

}
