package com.company.studer.services;

import com.company.studer.entities.Advertisement;
import com.company.studer.entities.Language;
import com.company.studer.entities.Phrase;
import com.company.studer.entities.PhraseCategory;
import com.company.studer.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    public Set<Set<Phrase>> getPromptsByPhrases(String phraseToCheck, Set<Phrase> foundPhrases) {
        Set<Set<Phrase>> checkedAndFoundPhrases = new HashSet<>();

        List<String> citiesMatch = getAdvertisementCitiesByActiveAndUserCityContaining(phraseToCheck);
        for (String city : citiesMatch) {
            Set<Phrase> singlePrompt = new LinkedHashSet<>();
            if (foundPhrases.size() > 0) {
                singlePrompt.addAll(foundPhrases);
            }
            singlePrompt.add(new Phrase(city, PhraseCategory.CITY));
            checkedAndFoundPhrases.add(singlePrompt);
        }
        List<String> universitiesMatch = getAdvertisementUniversitiesByActiveAndUserUniversityContaining(phraseToCheck);
        for (String university : universitiesMatch) {
            Set<Phrase> singlePrompt = new LinkedHashSet<>();
            if (foundPhrases.size() > 0) {
                singlePrompt.addAll(foundPhrases);
            }
            singlePrompt.add(new Phrase(university, PhraseCategory.UNIVERSITY));
            checkedAndFoundPhrases.add(singlePrompt);
        }

        return checkedAndFoundPhrases;
    }

    public List<Advertisement> getAdvertisementsByFilters(List<Phrase> filters) {
        Stream<Advertisement> advertisementStream = StreamSupport.stream(getAll().spliterator(), false);
        for (Phrase phrase : filters) {
            PhraseCategory phraseCategory = phrase.getCategory();
            String phraseValue = phrase.getPhrase();
            switch (phraseCategory) {
                case CITY:
                    advertisementStream = advertisementStream.filter(ad -> ad.getUser().getCity().equals(phraseValue));
                    break;
                case UNIVERSITY:
                    advertisementStream = advertisementStream.filter(ad -> ad.getUser().getUniversity().equals(phraseValue));
                    break;
                case AGE:
                    String[] splitAge = phraseValue.split("-");
                    int minAge = Integer.parseInt(splitAge[0]);
                    int maxAge = Integer.parseInt(splitAge[1]);
                    advertisementStream = advertisementStream.filter(ad -> ad.getUser().getAge() >= minAge);
                    advertisementStream = advertisementStream.filter(ad -> ad.getUser().getAge() <= maxAge);
                    break;
                case GENDER:
                    advertisementStream = advertisementStream.filter(ad -> ad.getUser().getGender().toString().equals(phraseValue));
                    break;
                case LANGUAGE:
                    Language parsedLanguage = null;
                    for (Language language : Language.values()) {
                        if (language.toString().equals(phraseValue)) {
                            parsedLanguage = language;
                        }
                    }
                    Language finalParsedLanguage = parsedLanguage;
                    if (!(finalParsedLanguage == null)) {
                        advertisementStream = advertisementStream.filter(ad -> ad.getUser().getLanguages().contains(finalParsedLanguage));
                    }
                    break;
            }
        }
        return advertisementStream.collect(Collectors.toList());
    }
}
