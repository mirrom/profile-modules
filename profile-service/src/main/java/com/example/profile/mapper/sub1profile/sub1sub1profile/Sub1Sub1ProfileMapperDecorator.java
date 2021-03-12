package com.example.profile.mapper.sub1profile.sub1sub1profile;

import com.example.profile.dto.sub1profile.sub1sub1profile.Sub1Sub1ProfileDto;
import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class Sub1Sub1ProfileMapperDecorator implements Sub1Sub1ProfileMapper {
    
    @Autowired
    @Qualifier("delegate")
    private Sub1Sub1ProfileMapper delegate;
    
    @Override
    public Sub1Sub1Profile dtoToModel(Sub1Sub1ProfileDto dto, ObjectId objectId) {
        
        Sub1Sub1Profile profile = delegate.dtoToModel(dto, objectId);
        
        profile.setId(objectId);
        
        return profile;
    }
    
    @Override
    public Sub1Sub1Profile dtoToModel(Sub1Sub1ProfileDto dto) {
        
        Sub1Sub1Profile profile = delegate.dtoToModel(dto);
        
        profile.setId(new ObjectId());
        
        return profile;
    }
    
}
