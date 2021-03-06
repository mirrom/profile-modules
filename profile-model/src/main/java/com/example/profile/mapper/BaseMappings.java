package com.example.profile.mapper;

import com.example.profile.dto.BaseDto;
import com.example.profile.model.BaseModel;

import org.bson.types.ObjectId;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@MapperConfig(uses = ObjectIdMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface BaseMappings {
    
    @Mapping(target = "id",
            ignore = true)
    @Mapping(target = "createdAt",
            ignore = true)
    @Mapping(target = "modifiedAt",
            ignore = true)
    BaseModel anyDtoToModel(BaseDto dto);
    
    @Mapping(target = "id",
            ignore = true)
    @Mapping(target = "createdAt",
            ignore = true)
    @Mapping(target = "modifiedAt",
            ignore = true)
    BaseModel anyDtoToModel(BaseDto dto, ObjectId objectId);
    
    BaseDto anyModelToDto(BaseModel model);
    
    void anyUpdateModel(BaseModel source, @MappingTarget BaseModel target);
    
}
