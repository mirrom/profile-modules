package com.example.profile.service;

import com.example.profile.model.Profile;
import com.example.profile.model.QProfile;
import com.example.profile.predicate.BasePredicateBuilder;
import com.example.profile.repository.BaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProfileService extends BaseService<Profile, QProfile> {
    
    @Autowired
    public ProfileService(BaseRepository<Profile, QProfile> repository) {
        
        super(repository);
    }
    
    @Override
    public Iterable<Profile> get(int page, int size, String sortDirection, String sortBy, String search) {
        
        BasePredicateBuilder<Profile> basePredicateBuilder =
                new BasePredicateBuilder<>(Profile.class, Profile.class.getName());
        
        basePredicateBuilder.from(search);
        
        var booleanExpression = basePredicateBuilder.build();
        
        return super.get(page, size, sortDirection, sortBy, booleanExpression);
    }
    
}
