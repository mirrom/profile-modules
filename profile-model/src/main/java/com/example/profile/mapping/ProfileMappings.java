package com.example.profile.mapping;

import com.example.profile.dto.ProfileDto;
import com.example.profile.model.Profile;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@MapperConfig(uses = ObjectIdMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMappings {
    
    @Mapping(target = "createdAt",
            ignore = true)
    @Mapping(target = "modifiedAt",
            ignore = true)
    Profile anyDtoToModel(ProfileDto profileDto);
    
    ProfileDto anyModelToDto(Profile profile);
    
    void anyUpdateModel(Profile source, @MappingTarget Profile target);
    
}
