package com.example.profile.config;

import com.example.profile.dto.ProfileDto;
import com.example.profile.dto.sub1profile.Sub1ProfileDto;
import com.example.profile.model.Profile;
import com.example.profile.model.sub1profile.Sub1Profile;

import org.bson.types.ObjectId;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {
    
    @Bean
    public ModelMapper pojoModelMapper() {
        
        ModelMapper modelMapper = new ModelMapper();
        
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        
        return modelMapper;
    }
    
    @Bean
    public ModelMapper dtoModelMapper() {
        
        ModelMapper modelMapper = new ModelMapper();
        
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        
        Converter<String, ObjectId> stringToObjectId = new AbstractConverter<>() {
            
            protected ObjectId convert(String id) {
                
                return (id != null && ObjectId.isValid(id)) ? new ObjectId(id) : null;
            }
        };
        
        Converter<ObjectId, String> objectIdToString = new AbstractConverter<>() {
            
            protected String convert(ObjectId objectId) {
                
                return objectId == null ? null : objectId.toString();
            }
        };
        
        TypeMap<ProfileDto, Profile> profileDtoToProfileTypeMap =
                modelMapper.createTypeMap(ProfileDto.class, Profile.class);
        
        profileDtoToProfileTypeMap.addMappings(mapper -> {
            
            mapper.using(stringToObjectId).map(ProfileDto::getId, Profile::setId);
    
            mapper.skip(Profile::setCreatedAt);
            mapper.skip(Profile::setModifiedAt);
        });
        
        TypeMap<Profile, ProfileDto> profileToProfileDtoTypeMap =
                modelMapper.createTypeMap(Profile.class, ProfileDto.class);
        
        profileToProfileDtoTypeMap.addMappings(mapper -> {
            
            mapper.using(objectIdToString).map(Profile::getId, ProfileDto::setId);
        });
        
                //profileToProfileDtoTypeMap.include(Sub1Profile.class, Sub1ProfileDto.class);
        
                //profileDtoToProfileTypeMap.include(Sub1ProfileDto.class, Sub1Profile.class);
        
        return modelMapper;
    }
    
}
