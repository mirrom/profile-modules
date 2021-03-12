package com.example.profile.mapper;

import com.example.profile.dto.LinkDto;
import com.example.profile.model.Link;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",
        config = LinkMappings.class)
@DecoratedWith(LinkMapperDecorator.class)
public interface LinkMapper {
    
    LinkDto modelToDto(Link link);
    
}
