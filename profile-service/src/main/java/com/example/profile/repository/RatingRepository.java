package com.example.profile.repository;

import com.example.profile.model.Rating;
import com.example.profile.property.RatingType;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RatingRepository extends MongoRepository<Rating, ObjectId> {
    
    void deleteByProfileId(ObjectId profileId);
    
    void deleteByProfileIdAndRatingType(ObjectId profileId, RatingType ratingType);
    
    void deleteByProfileIdAndUserId(ObjectId profileId, long userId);
    
    void deleteByProfileIdAndUserIdAndRatingType(ObjectId profileId, long userId, RatingType ratingType);
    
    List<Rating> findByProfileId(ObjectId profileId);
    
    Optional<Rating> findByProfileIdAndUserIdAndRatingType(ObjectId profileId, long userId, RatingType ratingType);
    
}
