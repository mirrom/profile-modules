package com.example.profile.predicate;

import com.example.profile.search.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class BasePredicateBuilder<P> {
    
    private final List<SearchCriteria> searchCriterias;
    private final Class<P> clazz;
    private final String collectionName;
    
    public BasePredicateBuilder(Class<P> clazz, String collectionName) {
        
        searchCriterias = new ArrayList<>();
        this.clazz = clazz;
        this.collectionName = collectionName;
    }
    
    public BooleanExpression build() {
        
        BooleanExpression result = getClassPredicate();
        
        final List<BooleanExpression> predicates = searchCriterias.stream().map(searchCriteria -> {
            BasePredicate<P> predicate = new BasePredicate<>(searchCriteria);
            return predicate.getPredicate(clazz, collectionName);
        }).filter(Objects::nonNull).collect(Collectors.toList());
        
        for (BooleanExpression predicate : predicates) {
            
            result = result.and(predicate);
        }
        
        return result;
    }
    
    public void from(String search) throws IllegalArgumentException {
        
        if (search != null) {
            
            Pattern pattern = Pattern.compile("([\\w.]+?)([:<>()])([\\w.\\- ]+?),");
            Matcher matcher = pattern.matcher(search + ",");
            
            while (matcher.find()) {
                
                SearchCriteria searchCriteria =
                        new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3));
                
                if (!searchCriteria.isValid()) {
                    
                    throw new IllegalArgumentException("Invalid query: " + searchCriteria.toString());
                }
                
                searchCriterias.add(searchCriteria);
            }
        }
    }
    
    public void with(final String field, final String comparator, final Object value) {
        
        searchCriterias.add(new SearchCriteria(field, comparator, value));
    }
    
    private BooleanExpression getClassPredicate() {
        
        PathBuilder<P> entityPathBuilder = new PathBuilder<>(clazz, collectionName);
        
        return entityPathBuilder.getString("_class").startsWithIgnoreCase(clazz.getPackageName());
    }
    
}
