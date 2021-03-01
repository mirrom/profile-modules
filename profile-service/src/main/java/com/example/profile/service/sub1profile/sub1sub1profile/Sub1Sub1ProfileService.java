package com.example.profile.service.sub1profile.sub1sub1profile;

import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;
import com.example.profile.predicate.sub1profile.sub1sub1profile.Sub1Sub1ProfilePredicatesBuilder;
import com.example.profile.repository.sub1profile.sub1sub1profile.Sub1Sub1ProfileRepository;
import com.example.profile.service.Serviceable;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class Sub1Sub1ProfileService implements Serviceable<Sub1Sub1Profile> {
    
    @Autowired
    private Sub1Sub1ProfileRepository sub1Sub1ProfileRepository;
    
    public Sub1Sub1Profile create(Sub1Sub1Profile sub1Sub1Profile) {
        
        return sub1Sub1ProfileRepository.save(sub1Sub1Profile);
    }
    
    @Override
    public void delete(ObjectId objectId) {
        
        sub1Sub1ProfileRepository.deleteById(objectId);
    }
    
    @Override
    public Iterable<Sub1Sub1Profile> get(int page, int size, String sortDirection, String sortBy, String search) {
        
        Sub1Sub1ProfilePredicatesBuilder sub1Sub1ProfilePredicatesBuilder = new Sub1Sub1ProfilePredicatesBuilder();
        
        if (search != null) {
            
            Pattern pattern = Pattern.compile("(\\w+?)([:<>()])(.+?),");
            Matcher matcher = pattern.matcher(search + ",");
            
            while (matcher.find()) {
                sub1Sub1ProfilePredicatesBuilder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        
        BooleanExpression booleanExpression = sub1Sub1ProfilePredicatesBuilder.build();
        
        if (booleanExpression != null) {
            
            return sub1Sub1ProfileRepository.findAll(booleanExpression,
                    PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
            
        } else {
            
            return sub1Sub1ProfileRepository
                    .findAll(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
        }
    }
    
    @Override
    public Optional<Sub1Sub1Profile> get(ObjectId objectId) {
        
        return sub1Sub1ProfileRepository.findById(objectId);
    }
    
    @Override
    public Sub1Sub1Profile update(Sub1Sub1Profile sub1Sub1Profile) {
        
        return sub1Sub1ProfileRepository.save(sub1Sub1Profile);
    }
    
}
