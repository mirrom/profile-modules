package com.example.profile.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection = "profiles")
@Entity
public class Profile {
    
    @Id
    private ObjectId id;
    
    @Indexed
    private String title;
    
    @Indexed
    private String description;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime modifiedAt;
    
}
