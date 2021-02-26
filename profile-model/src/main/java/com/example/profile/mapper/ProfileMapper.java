package com.example.profile.mapper;

import com.example.profile.dto.ProfileDto;
import com.example.profile.model.Profile;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(uses = ObjectIdMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProfileMapper {
    
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);
    
    @Mapping(target = "createdAt",
            ignore = true)
    @Mapping(target = "modifiedAt",
            ignore = true)
    Profile profileDtoToProfile(ProfileDto profileDto);
    
    ProfileDto profileToProfileDto(Profile profile);
    
    Profile updateProfile(Profile source, @MappingTarget Profile target);
    
}
