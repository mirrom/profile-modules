package com.example.profile.service.sub1profile;

import com.example.profile.model.sub1profile.Sub1Profile;
import com.example.profile.repository.sub1profile.Sub1ProfileRepository;
import com.example.profile.service.Serviceable;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class Sub1ProfileService implements Serviceable<Sub1Profile> {
    
    @Autowired
    private Sub1ProfileRepository sub1ProfileRepository;
    
    public Sub1Profile create(Sub1Profile sub1Profile) {
        
        return sub1ProfileRepository.save(sub1Profile);
    }
    
    @Override
    public void delete(ObjectId objectId) {
        
        sub1ProfileRepository.deleteById(objectId);
    }
    
    @Override
    public Iterable<Sub1Profile> get(int page, int size, String sortDirection, String sortBy) {
        
        return sub1ProfileRepository
                .findAll(PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
    }
    
    @Override
    public Optional<Sub1Profile> get(ObjectId objectId) {
        
        return sub1ProfileRepository.findById(objectId);
    }
    
    @Override
    public Sub1Profile update(Sub1Profile sub1Profile) {
        
        return sub1ProfileRepository.save(sub1Profile);
    }
    
}
