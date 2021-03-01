package com.example.profile.predicate.sub1profile;

import com.example.profile.search.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Sub1ProfilePredicatesBuilder {
    
    private final List<SearchCriteria> searchCriterias;
    
    public Sub1ProfilePredicatesBuilder() {
        
        searchCriterias = new ArrayList<>();
    }
    
    public Sub1ProfilePredicatesBuilder with(final String field, final String comparator, final Object value) {
        
        searchCriterias.add(new SearchCriteria(field, comparator, value));
        
        return this;
    }
    
    public BooleanExpression build() {
        
        BooleanExpression result = null;
        
        if (searchCriterias.isEmpty()) {
            
            return null;
        }
        
        final List<BooleanExpression> predicates = searchCriterias.stream().map(searchCriteria -> {
            Sub1ProfilePredicate predicate = new Sub1ProfilePredicate(searchCriteria);
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
