package com.company.studer.findBuddy.service;

import com.company.studer.findBuddy.entity.Advertisement;
import com.company.studer.findBuddy.entity.Criteria;
import com.company.studer.findBuddy.entity.CriteriaFactory;
import com.company.studer.findBuddy.entity.CriteriaType;
import com.company.studer.findBuddy.repository.AdvertisementRepository;
import com.company.studer.profile.entity.Gender;
import com.company.studer.profile.entity.Language;
import com.company.studer.common.service.CrudService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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

    public Set<Set<Criteria>> getPromptsByPhrases(String phraseToCheck, Set<Criteria> foundPhrases) {
        Set<Set<Criteria>> checkedAndFoundPhrases = new HashSet<>();

        List<String> citiesMatch = getAdvertisementCitiesByActiveAndUserCityContaining(phraseToCheck);
        for (String city : citiesMatch) {
            Set<Criteria> singlePrompt = new LinkedHashSet<>();
            if (foundPhrases.size() > 0) {
                singlePrompt.addAll(foundPhrases);
            }
            singlePrompt.add(new Criteria(city, CriteriaType.CITY, List.of(city)));
            checkedAndFoundPhrases.add(singlePrompt);
        }
        List<String> universitiesMatch = getAdvertisementUniversitiesByActiveAndUserUniversityContaining(phraseToCheck);
        for (String university : universitiesMatch) {
            Set<Criteria> singlePrompt = new LinkedHashSet<>();
            if (foundPhrases.size() > 0) {
                singlePrompt.addAll(foundPhrases);
            }
            singlePrompt.add(new Criteria(university, CriteriaType.UNIVERSITY, List.of(university)));
            checkedAndFoundPhrases.add(singlePrompt);
        }
        for (Language language : Language.values()) {
            Set<Criteria> singlePrompt = new LinkedHashSet<>();
            if (language.toString().startsWith(phraseToCheck)) {
                if (foundPhrases.size() > 0) {
                    singlePrompt.addAll(foundPhrases);
                }
                singlePrompt.add(new Criteria(language.toString(), CriteriaType.LANGUAGE, List.of(language.toString())));
                checkedAndFoundPhrases.add(singlePrompt);
            }
        }
        for (Gender gender : Gender.values()) {
            Set<Criteria> singlePrompt = new LinkedHashSet<>();
            if (gender.toString().startsWith(phraseToCheck)) {
                if (foundPhrases.size() > 0) {
                    singlePrompt.addAll(foundPhrases);
                }
                singlePrompt.add(new Criteria(gender.toString(), CriteriaType.GENDER, List.of(gender.toString())));
                checkedAndFoundPhrases.add(singlePrompt);
            }
        }
        for (Criteria ageCriteria : criteriaFactory.create(CriteriaType.AGE)) {
            Set<Criteria> singlePrompt = new LinkedHashSet<>();
            if (ageCriteria.getValue().contains(phraseToCheck)) {
                if (foundPhrases.size() > 0) {
                    singlePrompt.addAll(foundPhrases);
                }
                singlePrompt.add(new Criteria(ageCriteria.getLabel(), CriteriaType.AGE, ageCriteria.getValue()));
                checkedAndFoundPhrases.add(singlePrompt);
            }
        }

        return checkedAndFoundPhrases;
    }

    public List<Advertisement> getAdvertisementsByFilters(List<Criteria> filters) {
        Stream<Advertisement> advertisementStream = StreamSupport.stream(getAll().spliterator(), false);
        for (Criteria criteria : filters) {
            CriteriaType criteriaType = criteria.getType();
            List<String> criteriaValue = criteria.getValue();
            advertisementStream = filter(advertisementStream, criteriaType, criteriaValue);
        }
        return advertisementStream.collect(Collectors.toList());
    }

    private Stream<Advertisement> filter(Stream<Advertisement> advertisementStream,
                                         CriteriaType criteriaType, List<String> criteriaValue) {
        final int FIRST_ELEMENT = 0;
        switch (criteriaType) {
            case CITY -> advertisementStream = advertisementStream
                                               .filter(ad -> ad.getUser().getCity().equals(criteriaValue.get(FIRST_ELEMENT)));
            case UNIVERSITY -> advertisementStream = advertisementStream
                                                     .filter(ad -> ad.getUser().getUniversity().equals(criteriaValue.get(FIRST_ELEMENT)));
            case AGE -> {
                int minAge = Integer.parseInt(criteriaValue.get(0));
                int maxAge = Integer.parseInt(criteriaValue.get(2));
                advertisementStream = advertisementStream.filter(ad ->
                        Period.between(ad.getUser().getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() >= minAge);
                advertisementStream = advertisementStream.filter(ad ->
                        Period.between(ad.getUser().getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() <= maxAge);
            }
            case GENDER -> {
                String genderString = criteriaValue.get(FIRST_ELEMENT).charAt(0)
                                    + criteriaValue.get(FIRST_ELEMENT).substring(1).toLowerCase();
                System.out.println(genderString);
                advertisementStream = advertisementStream
                                                 .filter(ad -> ad.getUser().getGender().toString().equals(genderString));
            }
            case LANGUAGE -> {
                String languageString = criteriaValue.get(FIRST_ELEMENT).charAt(0)
                                      + criteriaValue.get(FIRST_ELEMENT).substring(1).toLowerCase();
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
