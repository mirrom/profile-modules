package com.example.profile.mapper;

import com.example.profile.dto.ProfileDto;
import com.example.profile.model.Profile;

import org.bson.types.ObjectId;


public abstract class ProfileMapperDecorator extends BaseMapperDecorator<Profile, ProfileDto> implements ProfileMapper {
    
    @Override
    public Profile dtoToModel(ProfileDto dto) {
        
        return super.dtoToModel(dto);
    }
    
    @Override
    public Profile dtoToModel(ProfileDto dto, ObjectId objectId) {
        
        return super.dtoToModel(dto, objectId);
    }
    
    @Override
    public ProfileDto modelToDto(Profile model) {
        
        return super.modelToDto(model);
    }
    
}
