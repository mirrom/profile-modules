package com.example.profile.mapper;

import com.example.profile.dto.RatingDto;
import com.example.profile.model.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class RatingMapperDecorator implements RatingMapper {
    
    @Autowired
    @Qualifier("delegate")
    private RatingMapper delegate;
    
    @Override
    public RatingDto modelToDto(Rating rating) {
        
        return delegate.modelToDto(rating);
    }
    
}
