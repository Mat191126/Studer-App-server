package com.company.studer.repositories;

import com.company.studer.entities.Place;
import com.company.studer.entities.PlaceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlaceRepository extends CrudRepositoryMethods<Place, UUID> {
    String statement = """
            SELECT place.id, place.description, place.name,
                   a.id, a.street, a.street_number, a.town, a.zip_code,
                   ptl.place_type_id, pt.type, 
                   l.id, ST_AsText(l.point) as point, ST_X(l.point) as long, ST_Y(l.point) as lat
            FROM place
                     LEFT JOIN address a on a.id = place.address_id
                     LEFT JOIN place_types_list ptl on place.id = ptl.place_id
                     LEFT JOIN place_type pt on pt.id = ptl.place_type_id
                     LEFT JOIN location l on l.id = a.location_id
            WHERE ST_DWithin(l.point::geography,
                             ST_GeogFromText(?3),
                             ?4, false)
              AND place.active = ?1
              AND pt.type in (?2);
            """;

    Iterable<Place> findPlaceByActiveAndPlaceTypesIn(boolean active, Iterable<PlaceType> placeTypes);

    @Query(statement)
    Iterable<Place> findPlaceByActiveAndTypesInRadius(boolean active, Iterable<PlaceType> placeTypes, String mapCenter, int radius);}
