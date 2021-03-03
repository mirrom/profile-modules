package com.example.profile.mapping;

import com.example.profile.dto.ProfileDto;
import com.example.profile.model.Profile;

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
public interface ProfileMappings {
    
    @Mapping(target = "id",
            ignore = true)
    @Mapping(target = "createdAt",
            ignore = true)
    @Mapping(target = "modifiedAt",
            ignore = true)
    Profile anyDtoToModel(ProfileDto profileDto);
    
    @Mapping(target = "id",
            ignore = true)
    @Mapping(target = "createdAt",
            ignore = true)
    @Mapping(target = "modifiedAt",
            ignore = true)
    Profile anyDtoToModel(ProfileDto profileDto, ObjectId objectId);
    
    ProfileDto anyModelToDto(Profile profile);
    
    void anyUpdateModel(Profile source, @MappingTarget Profile target);
    
}
