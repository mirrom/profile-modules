package com.example.profile.search;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SearchCriteria {
    
    private static final List<String> COMPARATORS = Arrays.asList("<", ">", ":", "(", ")");
    
    private String field;
    private String comparator;
    private Object value;
    
    public boolean isValid() {
        
        return field != null && COMPARATORS.contains(comparator) && value != null;
    }
    
}
