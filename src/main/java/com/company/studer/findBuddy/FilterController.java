package com.company.studer.findBuddy;

import com.company.studer.findBuddy.entity.Advertisement;
import com.company.studer.findBuddy.entity.Criteria;
import com.company.studer.findBuddy.entity.CriteriaFactory;
import com.company.studer.findBuddy.entity.CriteriaType;
import com.company.studer.findBuddy.service.AdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/advertisement")
public class FilterController {

    private final AdvertisementService advertisementService;
    private final CriteriaFactory criteriaFactory;

    public FilterController(AdvertisementService userService, CriteriaFactory criteriaFactory) {
        this.advertisementService = userService;
        this.criteriaFactory = criteriaFactory;
    }

    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    private Iterable<Advertisement> getAllByFilters(@RequestBody List<Criteria> filters) {
        return advertisementService.getAdvertisementsByFilters(filters);
    }

    @PostMapping("/search/{phraseToCheck}")
    @ResponseStatus(HttpStatus.OK)
    private Set<Set<Criteria>> searchForMultiplePrompt(@PathVariable String phraseToCheck,
                                                       @RequestBody Set<Criteria> foundPhrases) {
        return advertisementService.getPromptsByPhrases(phraseToCheck, foundPhrases);
    }
    @GetMapping("/search/{phraseToCheck}")
    @ResponseStatus(HttpStatus.OK)
    private Set<Set<Criteria>> searchForSinglePrompt(@PathVariable String phraseToCheck) {
        return advertisementService.getPromptsByPhrases(phraseToCheck, Collections.emptySet());
    }

    @GetMapping("/criteria")
    @ResponseStatus(HttpStatus.OK)
    private List<Criteria> getCriteriaList() {
        List<Criteria> criteria = new ArrayList<>();
        criteria.addAll(criteriaFactory.create(CriteriaType.AGE));
        criteria.addAll(criteriaFactory.create(CriteriaType.GENDER));
        criteria.addAll(criteriaFactory.create(CriteriaType.LANGUAGE));
        return criteria;
    }
}
