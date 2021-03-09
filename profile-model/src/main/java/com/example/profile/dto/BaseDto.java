package com.example.profile.dto;

import com.example.profile.property.Type;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public abstract class BaseDto {
    
    private String id;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime modifiedAt;
    
    private Type type;
    
}
