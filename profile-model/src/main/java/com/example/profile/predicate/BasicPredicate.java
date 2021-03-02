package com.example.profile.predicate;

import com.example.profile.search.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class BasicPredicate<P> {
    
    private final SearchCriteria searchCriteria;
    
    BasicPredicate(SearchCriteria searchCriteria) {
        
        this.searchCriteria = searchCriteria;
    }
    
    BooleanExpression getPredicate(Class<P> clazz, String collectionName) {
        
        PathBuilder<P> entityPathBuilder = new PathBuilder<>(clazz, collectionName);
        
        if (isDate(searchCriteria.getValue().toString())) {
            
            DatePath<LocalDate> localDateDatePath =
                    entityPathBuilder.getDate(searchCriteria.getField(), LocalDate.class);
            
            LocalDate value = LocalDate.parse(searchCriteria.getValue().toString());
            
            switch (searchCriteria.getComparator()) {
                
                default:
                case ":":
                    return localDateDatePath.eq(value);
                case ">":
                    return localDateDatePath.gt(value);
                case "<":
                    return localDateDatePath.lt(value);
                case "(":
                    return localDateDatePath.loe(value);
                case ")":
                    return localDateDatePath.goe(value);
            }
            
        } else if (isNumber(searchCriteria.getValue().toString())) {
            
            NumberPath<Integer> integerNumberPath =
                    entityPathBuilder.getNumber(searchCriteria.getField(), Integer.class);
            
            Integer value = Integer.parseInt(searchCriteria.getValue().toString());
            
            switch (searchCriteria.getComparator()) {
                
                default:
                case ":":
                    return integerNumberPath.eq(value);
                case ">":
                    return integerNumberPath.gt(value);
                case "<":
                    return integerNumberPath.lt(value);
                case "(":
                    return integerNumberPath.loe(value);
                case ")":
                    return integerNumberPath.goe(value);
            }
            
        } else {
            
            StringPath stringPath = entityPathBuilder.getString(searchCriteria.getField());
            
            if (searchCriteria.getComparator().equalsIgnoreCase(":")) {
                
                return stringPath.containsIgnoreCase(searchCriteria.getValue().toString());
            }
        }
        
        return null;
    }
    
    private boolean isDate(String value) {
        
        try {
            
            LocalDate.parse(value);
            
        } catch (DateTimeParseException e) {
            
            return false;
        }
        
        return true;
    }
    
    private boolean isNumber(String value) {
        
        try {
            
            Integer.parseInt(value);
            
        } catch (NumberFormatException e) {
            
            return false;
        }
        
        return true;
    }
    
}
