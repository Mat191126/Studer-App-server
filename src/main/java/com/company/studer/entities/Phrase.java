package com.company.studer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phrase {

    private String phrase;
    private PhraseCategory category;

}
