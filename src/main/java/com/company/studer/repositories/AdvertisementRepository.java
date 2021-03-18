package com.company.studer.repositories;

import com.company.studer.entities.Advertisement;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdvertisementRepository extends CrudRepositoryMethods<Advertisement, UUID> {

}
