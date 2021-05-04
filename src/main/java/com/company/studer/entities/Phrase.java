package com.company.studer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phrase {

    private String phrase;
    private PhraseCategory category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phrase phrase1 = (Phrase) o;
        return phrase.equals(phrase1.phrase) && category == phrase1.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phrase, category);
    }
}
