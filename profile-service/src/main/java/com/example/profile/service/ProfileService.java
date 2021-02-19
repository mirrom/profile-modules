package com.example.profile.service;

import com.example.profile.model.Profile;
import com.example.profile.repository.ProfileRepository;

import java.util.Optional;

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
    public Iterable<Profile> get(int page, int size, String sortDirection, String sortBy) {
        
        return profileRepository.findAll(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
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
