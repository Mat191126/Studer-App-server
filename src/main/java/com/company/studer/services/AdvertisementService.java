package com.company.studer.services;

import com.company.studer.entities.Advertisement;
import com.company.studer.entities.Phrase;
import com.company.studer.entities.PhraseCategory;
import com.company.studer.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdvertisementService extends CrudService<Advertisement, UUID> {

    private final AdvertisementRepository repository;

    public AdvertisementService(AdvertisementRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public boolean update(Advertisement newAdvertisement) {
        Optional<Advertisement> oldAdvertisementOptional = get(newAdvertisement.getId());
        if (oldAdvertisementOptional.isPresent()) {
            Advertisement oldAdvertisement = oldAdvertisementOptional.get();
            oldAdvertisement.setUser(newAdvertisement.getUser());
            oldAdvertisement.setTitle(newAdvertisement.getTitle());
            oldAdvertisement.setDescription(newAdvertisement.getDescription());
            add(oldAdvertisement);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID id) {
        Optional<Advertisement> object = get(id);
        if (object.isPresent()) {
            Advertisement oldObject = object.get();
            oldObject.setActive(false);
            repository.save(oldObject);
            return true;
        }
        return false;
    }

    private List<String> getAdvertisementCitiesByActiveAndUserCityContaining(String city) {
        List<Advertisement> advertisements = repository.findDistinctFirstAdvertisementsByActiveAndUserCityContaining(true, city);
        return advertisements.stream()
                             .map(advertisement -> advertisement.getUser().getCity())
                             .collect(Collectors.toList());
    }

    private List<String> getAdvertisementUniversitiesByActiveAndUserUniversityContaining(String university) {
        List<Advertisement> advertisements = repository.findDistinctFirstAdvertisementsByActiveAndUserUniversityContaining(true, university);
        return advertisements.stream()
                .map(advertisement -> advertisement.getUser().getUniversity())
                .collect(Collectors.toList());
    }

    public List<Phrase> getPromptsByPhrases(List<String> phrases) {
        List<Phrase> foundPhrases = new ArrayList<>();
        for (String phrase : phrases) {
            List<String> citiesMatch = getAdvertisementCitiesByActiveAndUserCityContaining(phrase);
            for (String city : citiesMatch) {
                foundPhrases.add(new Phrase(city, PhraseCategory.CITY));
            }
            List<String> universitiesMatch = getAdvertisementUniversitiesByActiveAndUserUniversityContaining(phrase);
            for (String university : universitiesMatch) {
                foundPhrases.add(new Phrase(university, PhraseCategory.UNIVERSITY));
            }
        }

        return foundPhrases;
    }

    public List<Advertisement> getAdvertisementsByActiveAndUserCity(String city) {
        return repository.findAdvertisementsByActiveAndUserCity(true, city);
    }
}
