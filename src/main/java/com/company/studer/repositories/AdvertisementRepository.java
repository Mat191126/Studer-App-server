package com.company.studer.repositories;

import com.company.studer.entities.Advertisement;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdvertisementRepository extends CrudRepositoryMethods<Advertisement, UUID> {

    List<Advertisement> findDistinctFirstAdvertisementsByActiveAndUserCityContaining(boolean active, String user_city);
}
