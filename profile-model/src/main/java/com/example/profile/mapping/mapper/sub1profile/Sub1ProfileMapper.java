package com.example.profile.mapping.mapper.sub1profile;

import com.example.profile.dto.sub1profile.Sub1ProfileDto;
import com.example.profile.mapping.ProfileMappings;
import com.example.profile.mapping.mapper.ProfileMapper;
import com.example.profile.model.sub1profile.Sub1Profile;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(config = ProfileMappings.class)
public interface Sub1ProfileMapper extends ProfileMapper {
    
    Sub1ProfileMapper INSTANCE = Mappers.getMapper(Sub1ProfileMapper.class);
    
    @InheritConfiguration(name = "anyDtoToModel")
    Sub1Profile dtoToModel(Sub1ProfileDto dto);
    
    @InheritConfiguration(name = "anyModelToDto")
    Sub1ProfileDto modelToDto(Sub1Profile model);
    
    @InheritConfiguration(name = "anyUpdateModel")
    void updateModel(Sub1Profile source, @MappingTarget Sub1Profile target);
    
}
