package com.example.profile.mapping.mapper;

import com.example.profile.dto.ProfileDto;
import com.example.profile.mapping.ProfileMappings;
import com.example.profile.model.Profile;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",
        config = ProfileMappings.class)
@DecoratedWith(ProfileMapperDecorator.class)
public interface ProfileMapper extends BaseMapper<Profile, ProfileDto> {
    
}
