package com.example.profile.mapper.sub1profile.sub1sub1profile;

import com.example.profile.dto.sub1profile.sub1sub1profile.Sub1Sub1ProfileDto;
import com.example.profile.mapper.ObjectIdMapper;
import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(uses = ObjectIdMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface Sub1Sub1ProfileMapper {
    
    Sub1Sub1ProfileMapper INSTANCE = Mappers.getMapper(Sub1Sub1ProfileMapper.class);
    
    @Mapping(target = "createdAt",
            ignore = true)
    @Mapping(target = "modifiedAt",
            ignore = true)
    Sub1Sub1ProfileDto sub1Sub1ProfileToSub1Sub1ProfileDto(Sub1Sub1Profile sub1Sub1Profile);
    
    Sub1Sub1Profile sub1Sub1profileDtoToSub1Sub1Profile(Sub1Sub1ProfileDto sub1Sub1ProfileDto);
    
    Sub1Sub1Profile updateSub1Sub1Profile(Sub1Sub1Profile source, @MappingTarget Sub1Sub1Profile target);
    
}
