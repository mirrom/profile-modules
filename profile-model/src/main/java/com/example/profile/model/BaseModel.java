package com.example.profile.model;

import com.example.profile.property.Type;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection = "profiles")
@Entity
public abstract class BaseModel {
    
    @Id
    private ObjectId id;
    
    @Version
    public Integer version;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    
    @Transient
    private Type type;
    
}
