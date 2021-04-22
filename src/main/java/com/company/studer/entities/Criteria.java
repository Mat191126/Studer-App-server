package com.company.studer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {
    private String label;
    private CriteriaType type;
    private String value;
}
