package com.example.profile.mapping.mapper;

import com.example.profile.dto.ProfileDto;
import com.example.profile.mapping.ProfileMappings;
import com.example.profile.model.Profile;

import org.bson.types.ObjectId;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring",
        config = ProfileMappings.class)
@DecoratedWith(ProfileMapperDecorator.class)
public interface ProfileMapper {
    
    Profile dtoToModel(ProfileDto profileDto);
    
    Profile dtoToModel(ProfileDto dto, ObjectId objectId);
    
    ProfileDto modelToDto(Profile model);
    
    void updateModel(Profile source, @MappingTarget Profile target);
    
}
