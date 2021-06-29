package com.company.studer.findBuddy.repository;

import com.company.studer.common.repository.CrudRepositoryMethods;
import com.company.studer.findBuddy.entity.Advertisement;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdvertisementRepository extends CrudRepositoryMethods<Advertisement, UUID> {

    List<Advertisement> findDistinctAdvertisementsByActiveAndUserCityStartsWith(boolean active, String userCity);

    List<Advertisement> findDistinctAdvertisementsByActiveAndUserUniversityStartsWith(boolean active, String userUniversity);
}
