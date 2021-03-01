package com.example.profile.service;

import com.example.profile.model.Profile;
import com.example.profile.predicate.ProfilePredicatesBuilder;
import com.example.profile.repository.ProfileRepository;
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
public class ProfileService implements Serviceable<Profile> {
    
    @Autowired
    private ProfileRepository profileRepository;
    
    public Profile create(Profile profile) {
        
        return profileRepository.save(profile);
    }
    
    @Override
    public void delete(ObjectId objectId) {
        
        profileRepository.deleteById(objectId);
    }
    
    @Override
    public Iterable<Profile> get(int page, int size, String sortDirection, String sortBy, String search) {
        
        ProfilePredicatesBuilder profilePredicatesBuilder = new ProfilePredicatesBuilder();
        
        if (search != null) {
            
            Pattern pattern = Pattern.compile("(\\w+?)([:<>()])(.+?),");
            Matcher matcher = pattern.matcher(search + ",");
            
            while (matcher.find()) {
                profilePredicatesBuilder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        
        BooleanExpression booleanExpression = profilePredicatesBuilder.build();
        
        if (booleanExpression != null) {
            
            return profileRepository.findAll(booleanExpression,
                    PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
            
        } else {
            
            return profileRepository
                    .findAll(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
        }
    }
    
    @Override
    public Optional<Profile> get(ObjectId objectId) {
        
        return profileRepository.findById(objectId);
    }
    
    @Override
    public Profile update(Profile profile) {
        
        return profileRepository.save(profile);
    }
    
}
