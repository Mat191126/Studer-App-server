package com.company.studer.repositories;

import com.company.studer.entities.Location;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationRepository extends CrudRepositoryMethods<Location, UUID> {

}
