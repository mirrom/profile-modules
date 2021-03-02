package com.example.profile.mapping.mapper.sub1profile.sub1sub1profile;

import com.example.profile.dto.sub1profile.sub1sub1profile.Sub1Sub1ProfileDto;
import com.example.profile.mapping.ProfileMappings;
import com.example.profile.mapping.mapper.sub1profile.Sub1ProfileMapper;
import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(config = ProfileMappings.class)
public interface Sub1Sub1ProfileMapper extends Sub1ProfileMapper {
    
    Sub1Sub1ProfileMapper INSTANCE = Mappers.getMapper(Sub1Sub1ProfileMapper.class);
    
    @InheritConfiguration(name = "anyDtoToModel")
    Sub1Sub1Profile dtoToModel(Sub1Sub1ProfileDto dto);
    
    @InheritConfiguration(name = "anyModelToDto")
    Sub1Sub1ProfileDto modelToDto(Sub1Sub1Profile model);
    
    @InheritConfiguration(name = "anyUpdateModel")
    void updateModel(Sub1Sub1Profile source, @MappingTarget Sub1Sub1Profile target);
    
}
