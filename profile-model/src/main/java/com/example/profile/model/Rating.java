package com.example.profile.model;

import com.example.profile.property.RatingType;

import javax.persistence.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection = "ratings")
@Entity
public class Rating {
    
    @Id
    private ObjectId id;
    
    private ObjectId profileId;
    
    private long userId;
    
    private RatingType ratingType;
    
    private Integer userRating;
    
}
