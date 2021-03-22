package com.company.studer.repositories;

import com.company.studer.entities.Place;
import com.company.studer.entities.PlaceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository extends CrudRepositoryMethods<Place, UUID> {
    String statement = """
             SELECT place.id, place.active, place.description, place.name,
                   a.id as address_id,
                   ptl.place_type_id, pt.type, l.id, l.point
             FROM place
                     LEFT JOIN address a on a.id = place.address_id
                     LEFT JOIN place_types_list ptl on place.id = ptl.place_id
                     LEFT JOIN place_type pt on pt.id = ptl.place_type_id
                     LEFT JOIN location l on l.id = a.location_id
             WHERE ST_DWithin(CAST (l.point AS geography),
                              ST_GeogFromText(:center),
                              :radius, false)
               AND place.active = :active
               AND pt.id in (:types)
            """;

    Iterable<Place> findPlaceByActiveAndPlaceTypesIn(boolean active, Iterable<PlaceType> placeTypes);

    @Query(value = statement, nativeQuery = true)
    Iterable<Place> findPlaceByActiveAndTypesInRadius(@Param("center") String mapCenter,
                                                      @Param("radius") int radius,
                                                      @Param("active") boolean active,
                                                      @Param("types")Iterable<PlaceType> placeTypes);

}
