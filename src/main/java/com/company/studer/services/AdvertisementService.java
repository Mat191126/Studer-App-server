package com.company.studer.services;

import com.company.studer.entities.Advertisement;
import com.company.studer.entities.Phrase;
import com.company.studer.entities.PhraseCategory;
import com.company.studer.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.*;
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
        List<Advertisement> advertisements = repository.findDistinctAdvertisementsByActiveAndUserCityStartsWith(true, city);
        return advertisements.stream()
                             .map(advertisement -> advertisement.getUser().getCity())
                             .collect(Collectors.toList());
    }

    private List<String> getAdvertisementUniversitiesByActiveAndUserUniversityContaining(String university) {
        List<Advertisement> advertisements = repository.findDistinctAdvertisementsByActiveAndUserUniversityStartsWith(true, university);
        return advertisements.stream()
                .map(advertisement -> advertisement.getUser().getUniversity())
                .collect(Collectors.toList());
    }

    public Set<String> getPromptsByPhrases(List<String> phrases) {
        Set<String> foundPhrases = new HashSet<>();
        String lastPhraseToCheck = phrases.get(phrases.size() - 1);
        StringBuilder checkedPhrasesBuilder = new StringBuilder();

        if (phrases.size() > 1) {
            for (int phraseIndex = 0; phraseIndex < phrases.size() - 1; phraseIndex++) {
                String checkedPhrase = phrases.get(phraseIndex);
                checkedPhrasesBuilder.append(checkedPhrase);
                checkedPhrasesBuilder.append(" ");
            }
        }
        String checkedPhrases = checkedPhrasesBuilder.toString();

        List<String> citiesMatch = getAdvertisementCitiesByActiveAndUserCityContaining(lastPhraseToCheck);
        for (String city : citiesMatch) {
            foundPhrases.add(checkedPhrases + city);
        }
        List<String> universitiesMatch = getAdvertisementUniversitiesByActiveAndUserUniversityContaining(lastPhraseToCheck);
        for (String university : universitiesMatch) {
            foundPhrases.add(checkedPhrases + university);
        }

        return foundPhrases;
    }

    public List<Advertisement> getAdvertisementsByActiveAndUserCity(String city) {
        return repository.findAdvertisementsByActiveAndUserCity(true, city);
    }
}
