package com.example.profile.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.MappingTarget;


public interface BaseMapper<M, D> {
    
    M dtoToModel(D dto);
    
    M dtoToModel(D dto, ObjectId objectId);
    
    D modelToDto(M model);
    
    void updateModel(M source, @MappingTarget M target);
    
}
