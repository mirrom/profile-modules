package com.example.profile.predicate;

import com.example.profile.model.BaseModel;
import com.example.profile.search.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class BasePredicate<M extends BaseModel> {
    
    private final SearchCriteria searchCriteria;
    
    BasePredicate(SearchCriteria searchCriteria) {
        
        this.searchCriteria = searchCriteria;
    }
    
    BooleanExpression getPredicate(Class<M> clazz, String collectionName) {
        
        var entityPathBuilder = new PathBuilder<>(clazz, collectionName);
        
        if (isDate(searchCriteria.getValue().toString())) {
            
            var localDateDatePath = entityPathBuilder.getDate(searchCriteria.getField(), LocalDate.class);
            
            var value = LocalDate.parse(searchCriteria.getValue().toString());
            
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
            
            var integerNumberPath = entityPathBuilder.getNumber(searchCriteria.getField(), Integer.class);
            
            var value = Integer.parseInt(searchCriteria.getValue().toString());
            
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
            
            var stringPath = entityPathBuilder.getString(searchCriteria.getField());
            
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
