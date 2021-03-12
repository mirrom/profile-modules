package com.example.profile.mapper;

import com.example.profile.dto.ProfileDto;
import com.example.profile.model.Profile;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",
        config = BaseMappings.class)
@DecoratedWith(ProfileMapperDecorator.class)
public interface ProfileMapper extends BaseMapper<Profile, ProfileDto> {
    
}
