package com.example.profile.mapping.mapper.sub1profile;

import com.example.profile.dto.sub1profile.Sub1ProfileDto;
import com.example.profile.mapping.ProfileMappings;
import com.example.profile.mapping.mapper.BaseMapper;
import com.example.profile.model.sub1profile.Sub1Profile;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",
        config = ProfileMappings.class)
@DecoratedWith(Sub1ProfileMapperDecorator.class)
public interface Sub1ProfileMapper extends BaseMapper<Sub1Profile, Sub1ProfileDto> {
    
}
