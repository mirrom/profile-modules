package com.example.profile.mapping.mapper;

import com.example.profile.dto.ProfileDto;
import com.example.profile.mapping.ProfileMappings;
import com.example.profile.model.Profile;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(config = ProfileMappings.class)
public interface ProfileMapper {
    
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);
    
    @InheritConfiguration(name = "anyDtoToModel")
    Profile dtoToModel(ProfileDto dto);
    
    @InheritConfiguration(name = "anyModelToDto")
    ProfileDto modelToDto(Profile model);
    
    @InheritConfiguration(name = "anyUpdateModel")
    void updateModel(Profile source, @MappingTarget Profile target);
    
}
