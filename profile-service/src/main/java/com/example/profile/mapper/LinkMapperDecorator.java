package com.example.profile.mapper;

import com.example.profile.dto.LinkDto;
import com.example.profile.model.Link;
import com.example.profile.service.ProfileService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class LinkMapperDecorator implements LinkMapper {
    
    @Autowired
    @Qualifier("delegate")
    private LinkMapper delegate;
    
    @Autowired
    private ProfileService profileService;
    
    @Override
    public LinkDto modelToDto(Link link) {
        
        var linkDto = delegate.modelToDto(link);
        
        profileService.get(new ObjectId(linkDto.getSourceId()))
                .ifPresent(profile -> linkDto.setSourceTitle(profile.getTitle()));
        profileService.get(new ObjectId(linkDto.getTargetId()))
                .ifPresent(profile -> linkDto.setTargetTitle(profile.getTitle()));
        
        return linkDto;
    }
    
}
