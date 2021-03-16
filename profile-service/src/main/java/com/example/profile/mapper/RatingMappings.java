package com.example.profile.mapper;

import com.example.profile.dto.RatingDto;
import com.example.profile.model.Rating;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@MapperConfig(uses = ObjectIdMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface RatingMappings {
    
    RatingDto modelToDto(Rating rating);
    
}
