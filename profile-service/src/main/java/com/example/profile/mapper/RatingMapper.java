package com.example.profile.mapper;

import com.example.profile.dto.RatingDto;
import com.example.profile.model.Rating;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",
        config = RatingMappings.class)
@DecoratedWith(RatingMapperDecorator.class)
public interface RatingMapper {
    
    RatingDto modelToDto(Rating rating);
    
}
