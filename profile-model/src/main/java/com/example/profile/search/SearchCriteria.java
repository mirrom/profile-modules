package com.example.profile.search;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SearchCriteria {
    
    private String field;
    private String comparator;
    private Object value;
    
    public SearchCriteria(final String field, final String comparator, final Object value) {
        
        super();
        
        setField(field);
        setComparator(comparator);
        setValue(value);
    }
    
}
