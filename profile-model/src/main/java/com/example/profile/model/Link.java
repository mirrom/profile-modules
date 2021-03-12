package com.example.profile.model;

import com.example.profile.property.LinkType;

import javax.persistence.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection = "links")
@Entity
public class Link {
    
    @Id
    private ObjectId id;
    
    private ObjectId sourceId;
    
    private ObjectId targetId;
    
    private LinkType linkType;
    
}
