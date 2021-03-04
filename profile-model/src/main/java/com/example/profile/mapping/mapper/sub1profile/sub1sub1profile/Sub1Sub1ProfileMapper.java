package com.example.profile.mapping.mapper.sub1profile.sub1sub1profile;

import com.example.profile.dto.sub1profile.sub1sub1profile.Sub1Sub1ProfileDto;
import com.example.profile.mapping.ProfileMappings;
import com.example.profile.mapping.mapper.BaseMapper;
import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",
        config = ProfileMappings.class)
@DecoratedWith(Sub1Sub1ProfileMapperDecorator.class)
public interface Sub1Sub1ProfileMapper extends BaseMapper<Sub1Sub1Profile, Sub1Sub1ProfileDto> {
    
}
