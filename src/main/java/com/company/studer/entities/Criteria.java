package com.company.studer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {
    private String label;
    private CriteriaType type;
    private List<String> value;
}
