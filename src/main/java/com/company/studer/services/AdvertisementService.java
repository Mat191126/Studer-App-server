package com.company.studer.services;

import com.company.studer.entities.*;
import com.company.studer.repositories.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class AdvertisementService extends CrudService<Advertisement, UUID> {

    private final AdvertisementRepository repository;
    private final CriteriaFactory criteriaFactory;

    public AdvertisementService(AdvertisementRepository repository, CriteriaFactory criteriaFactory) {
        super(repository);
        this.repository = repository;
        this.criteriaFactory = criteriaFactory;
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
        for (Language language : Language.values()) {
            Set<Phrase> singlePrompt = new LinkedHashSet<>();
            if (language.toString().startsWith(phraseToCheck)) {
                if (foundPhrases.size() > 0) {
                    singlePrompt.addAll(foundPhrases);
                }
                singlePrompt.add(new Phrase(language.toString(), PhraseCategory.LANGUAGE));
                checkedAndFoundPhrases.add(singlePrompt);
            }
        }
        for (Gender gender : Gender.values()) {
            Set<Phrase> singlePrompt = new LinkedHashSet<>();
            if (gender.toString().startsWith(phraseToCheck)) {
                if (foundPhrases.size() > 0) {
                    singlePrompt.addAll(foundPhrases);
                }
                singlePrompt.add(new Phrase(gender.toString(), PhraseCategory.GENDER));
                checkedAndFoundPhrases.add(singlePrompt);
            }
        }
        for (Criteria ageCriteria : criteriaFactory.create(CriteriaType.AGE)) {
            Set<Phrase> singlePrompt = new LinkedHashSet<>();
            if (ageCriteria.getValue().contains(phraseToCheck)) {
                if (foundPhrases.size() > 0) {
                    singlePrompt.addAll(foundPhrases);
                }
                String ageString = ageCriteria.getValue()
                        .toString()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "");
                singlePrompt.add(new Phrase(ageString, PhraseCategory.AGE));
                checkedAndFoundPhrases.add(singlePrompt);
            }
        }

        return checkedAndFoundPhrases;
    }

    public List<Advertisement> getAdvertisementsByFilters(List<Phrase> filters) {
        Stream<Advertisement> advertisementStream = StreamSupport.stream(getAll().spliterator(), false);
        for (Phrase phrase : filters) {
            PhraseCategory phraseCategory = phrase.getCategory();
            String phraseValue = phrase.getPhrase();
            advertisementStream = filter(advertisementStream, phraseCategory, phraseValue);
        }
        return advertisementStream.collect(Collectors.toList());
    }

    private Stream<Advertisement> filter(Stream<Advertisement> advertisementStream,
                                         PhraseCategory phraseCategory, String phraseValue) {
        switch (phraseCategory) {
            case CITY -> advertisementStream = advertisementStream
                                               .filter(ad -> ad.getUser().getCity().equals(phraseValue));
            case UNIVERSITY -> advertisementStream = advertisementStream
                                                     .filter(ad -> ad.getUser().getUniversity().equals(phraseValue));
            case AGE -> {
                String[] splitAge = phraseValue.split(",");
                int minAge = Integer.parseInt(splitAge[0]);
                int maxAge = Integer.parseInt(splitAge[2]);
                advertisementStream = advertisementStream.filter(ad -> ad.getUser().getAge() >= minAge);
                advertisementStream = advertisementStream.filter(ad -> ad.getUser().getAge() <= maxAge);
            }
            case GENDER -> {
                String genderString = phraseValue.charAt(0) + phraseValue.substring(1).toLowerCase();
                System.out.println(genderString);
                advertisementStream = advertisementStream
                                                 .filter(ad -> ad.getUser().getGender().toString().equals(genderString));
            }
            case LANGUAGE -> {
                String languageString = phraseValue.charAt(0) + phraseValue.substring(1).toLowerCase();
                System.out.println(languageString);
                Language parsedLanguage = null;
                for (Language language : Language.values()) {
                    if (language.toString().equals(languageString)) {
                        parsedLanguage = language;
                    }
                }
                Language finalParsedLanguage = parsedLanguage;
                if (!(finalParsedLanguage == null)) {
                    advertisementStream = advertisementStream
                                          .filter(ad -> ad.getUser().getLanguages().contains(finalParsedLanguage));
                }
            }
        }
        return advertisementStream;
    }
}
