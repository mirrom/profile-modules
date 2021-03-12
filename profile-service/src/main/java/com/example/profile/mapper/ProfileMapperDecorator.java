package com.example.profile.mapper;

import com.example.profile.dto.ProfileDto;
import com.example.profile.model.Profile;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class ProfileMapperDecorator implements ProfileMapper {
    
    @Autowired
    @Qualifier("delegate")
    private ProfileMapper delegate;
    
    @Override
    public Profile dtoToModel(ProfileDto dto, ObjectId objectId) {
        
        Profile profile = delegate.dtoToModel(dto, objectId);
        
        profile.setId(objectId);
        
        return profile;
    }
    
    @Override
    public Profile dtoToModel(ProfileDto dto) {
        
        Profile profile = delegate.dtoToModel(dto);
        
        profile.setId(new ObjectId());
        
        return profile;
    }
    
}
