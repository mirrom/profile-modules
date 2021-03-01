package com.example.profile.predicate.sub1profile;

import com.example.profile.model.sub1profile.Sub1Profile;
import com.example.profile.search.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;


public class Sub1ProfilePredicate {
    
    private SearchCriteria searchCriteria;
    
    public Sub1ProfilePredicate(final SearchCriteria searchCriteria) {
        
        this.searchCriteria = searchCriteria;
    }
    
    public SearchCriteria getCriteria() {
        
        return searchCriteria;
    }
    
    public BooleanExpression getPredicate() {
        
        PathBuilder<Sub1Profile> entityPath = new PathBuilder<>(Sub1Profile.class, "sub1Profile");
        
        if (isNumeric(searchCriteria.getValue().toString())) {
            
            NumberPath<Integer> integerNumberPath = entityPath.getNumber(searchCriteria.getField(), Integer.class);
            
            int value = Integer.parseInt(searchCriteria.getValue().toString());
            
            switch (searchCriteria.getComparator()) {
                
                case ">":
                    return integerNumberPath.gt(value);
                case "<":
                    return integerNumberPath.lt(value);
                case "(":
                    return integerNumberPath.loe(value);
                case ")":
                    return integerNumberPath.goe(value);
                default:
                case ":":
                    return integerNumberPath.eq(value);
            }
            
        } else {
            
            StringPath stringPath = entityPath.getString(searchCriteria.getField());
            
            if (searchCriteria.getComparator().equalsIgnoreCase(":")) {
                
                return stringPath.containsIgnoreCase(searchCriteria.getValue().toString());
            }
        }
        
        return null;
    }
    
    public void setCriteria(final SearchCriteria searchCriteria) {
        
        this.searchCriteria = searchCriteria;
    }
    
    private static boolean isNumeric(final String string) {
        
        try {
            Integer.parseInt(string);
        } catch (final NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
}
