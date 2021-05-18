package com.example.profile.mapper;

import com.example.profile.dto.BaseDto;
import com.example.profile.model.BaseModel;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class BaseMapperDecorator<M extends BaseModel, D extends BaseDto> implements BaseMapper<M, D> {
    
    @Autowired
    @Qualifier("delegate")
    private BaseMapper<M, D> delegate;
    
    @Override
    public M dtoToModel(D dto, ObjectId objectId) {
        
        var model = delegate.dtoToModel(dto, objectId);
        
        model.setId(objectId);
        
        return model;
    }
    
    @Override
    public M dtoToModel(D dto) {
        
        var model = delegate.dtoToModel(dto);
        
        model.setId(new ObjectId());
        
        return model;
    }
    
    @Override
    public D modelToDto(M model) {
        
        return delegate.modelToDto(model);
    }
    
}
