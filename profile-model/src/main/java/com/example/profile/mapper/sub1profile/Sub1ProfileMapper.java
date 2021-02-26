package com.example.profile.mapper.sub1profile;

import com.example.profile.dto.sub1profile.Sub1ProfileDto;
import com.example.profile.mapper.ObjectIdMapper;
import com.example.profile.model.sub1profile.Sub1Profile;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;


@Mapper(uses = ObjectIdMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface Sub1ProfileMapper {
    
    Sub1ProfileMapper INSTANCE = Mappers.getMapper(Sub1ProfileMapper.class);
    
    @Mapping(target = "createdAt",
            ignore = true)
    @Mapping(target = "modifiedAt",
            ignore = true)
    Sub1ProfileDto sub1ProfileToSub1ProfileDto(Sub1Profile sub1Profile);
    
    Sub1Profile sub1profileDtoToSub1Profile(Sub1ProfileDto sub1ProfileDto);
    
    Sub1Profile updateSub1Profile(Sub1Profile source, @MappingTarget Sub1Profile target);
    
}
