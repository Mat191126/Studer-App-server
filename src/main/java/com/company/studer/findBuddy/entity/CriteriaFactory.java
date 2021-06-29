package com.company.studer.findBuddy.entity;

import com.company.studer.profile.entity.Gender;
import com.company.studer.profile.entity.Language;
import com.company.studer.profile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CriteriaFactory {

    private final UserService userService;

    @Autowired
    public CriteriaFactory(UserService userService) {
        this.userService = userService;
    }

    public List<Criteria> create(CriteriaType criteriaType) {
        List<Criteria> criteria = new ArrayList<>();

        if (criteriaType.equals(CriteriaType.AGE)) {
            int minimumAge = Period.between(userService.getMinimumUserAge(), LocalDate.now()).getYears();
            int maximumAge = Period.between(userService.getMaximumUserAge(), LocalDate.now()).getYears();
            for (int age = minimumAge; age <= maximumAge; age += 3) {
                List<String> ageValues = new ArrayList<>();
                ageValues.add(String.valueOf(age));
                ageValues.add(String.valueOf(age + 1));
                ageValues.add(String.valueOf(age + 2));
                String label = String.format("%s - %s", age, age + 2);
                criteria.add(new Criteria(label, CriteriaType.AGE, ageValues));
            }
        }
        else if (criteriaType.equals(CriteriaType.GENDER)) {
            for (Gender gender : Gender.values()) {
                criteria.add(new Criteria(gender.toString(), CriteriaType.GENDER, Collections.singletonList(gender.toString().toUpperCase())));
            }
        }
        else if (criteriaType.equals(CriteriaType.LANGUAGE)) {
            for (Language language : Language.values()) {
                criteria.add(new Criteria(language.toString(), CriteriaType.LANGUAGE, Collections.singletonList(language.toString().toUpperCase())));
            }
        }
        return criteria;
    }
}