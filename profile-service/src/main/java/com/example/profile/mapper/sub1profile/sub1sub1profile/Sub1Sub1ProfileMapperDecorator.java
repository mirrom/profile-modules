package com.example.profile.mapper.sub1profile.sub1sub1profile;

import com.example.profile.dto.sub1profile.sub1sub1profile.Sub1Sub1ProfileDto;
import com.example.profile.mapper.BaseMapperDecorator;
import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;

import org.bson.types.ObjectId;


public abstract class Sub1Sub1ProfileMapperDecorator extends BaseMapperDecorator<Sub1Sub1Profile, Sub1Sub1ProfileDto>
        implements Sub1Sub1ProfileMapper {
    
    @Override
    public Sub1Sub1Profile dtoToModel(Sub1Sub1ProfileDto dto) {
        
        return super.dtoToModel(dto);
    }
    
    @Override
    public Sub1Sub1Profile dtoToModel(Sub1Sub1ProfileDto dto, ObjectId objectId) {
        
        return super.dtoToModel(dto, objectId);
    }
    
    @Override
    public Sub1Sub1ProfileDto modelToDto(Sub1Sub1Profile model) {
        
        return super.modelToDto(model);
    }
    
}
