package com.example.profile.mapping.mapper.sub1profile;

import com.example.profile.dto.sub1profile.Sub1ProfileDto;
import com.example.profile.mapping.ProfileMappings;
import com.example.profile.model.sub1profile.Sub1Profile;

import org.bson.types.ObjectId;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring",
        config = ProfileMappings.class)
@DecoratedWith(Sub1ProfileMapperDecorator.class)
public interface Sub1ProfileMapper {
    
    Sub1Profile dtoToModel(Sub1ProfileDto dto);
    
    Sub1Profile dtoToModel(Sub1ProfileDto dto, ObjectId objectId);
    
    Sub1ProfileDto modelToDto(Sub1Profile model);
    
    void updateModel(Sub1Profile source, @MappingTarget Sub1Profile target);
    
}
