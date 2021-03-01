package com.example.profile.predicate;

import com.example.profile.search.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ProfilePredicatesBuilder {
    
    private final List<SearchCriteria> searchCriterias;
    
    public ProfilePredicatesBuilder() {
        
        searchCriterias = new ArrayList<>();
    }
    
    public ProfilePredicatesBuilder with(final String field, final String comparator, final Object value) {
        
        searchCriterias.add(new SearchCriteria(field, comparator, value));
        
        return this;
    }
    
    public BooleanExpression build() {
        
        BooleanExpression result = null;
        
        if (searchCriterias.isEmpty()) {
            
            return null;
        }
        
        final List<BooleanExpression> predicates = searchCriterias.stream().map(searchCriteria -> {
            ProfilePredicate predicate = new ProfilePredicate(searchCriteria);
            return predicate.getPredicate();
        }).filter(Objects::nonNull).collect(Collectors.toList());
        
        for (BooleanExpression predicate : predicates) {
            
            if (result == null) {
                result = predicate;
            } else {
                result = result.and(predicate);
            }
        }
        
        return result;
    }
    
}
