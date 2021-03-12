package com.example.profile.mapper;

import com.example.profile.dto.BaseDto;
import com.example.profile.model.BaseModel;

import org.bson.types.ObjectId;
import org.mapstruct.MappingTarget;


public interface BaseMapper<M extends BaseModel, D extends BaseDto> {
    
    M dtoToModel(D dto);
    
    M dtoToModel(D dto, ObjectId objectId);
    
    D modelToDto(M model);
    
    void updateModel(M source, @MappingTarget M target);
    
}
