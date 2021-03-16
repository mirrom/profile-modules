package com.example.profile.service;

import com.example.profile.model.Rating;
import com.example.profile.property.RatingType;
import com.example.profile.repository.RatingRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RatingService {
    
    @Autowired
    RatingRepository ratingRepository;
    
    public Rating createOrUpdate(ObjectId profileId, Long userId, RatingType ratingType, Integer userRating) {
        
        Rating rating =
                ratingRepository.findByProfileIdAndUserIdAndRatingType(profileId, userId, ratingType).orElseGet(() -> {
                    
                    Rating newRating = new Rating();
                    
                    newRating.setId(new ObjectId());
                    newRating.setProfileId(profileId);
                    newRating.setUserId(userId);
                    newRating.setRatingType(ratingType);
                    
                    return newRating;
                });
        
        rating.setUserRating(userRating);
        
        return ratingRepository.save(rating);
    }
    
    public void delete(ObjectId profileId, Long userId, RatingType ratingType) {
        
        if (userId != null && ratingType != null) {
            
            deleteByProfileIdAndUserIdAndRatingType(profileId, userId, ratingType);
            
        } else if (userId != null) {
            
            deleteByProfileIdAndUserId(profileId, userId);
            
        } else if (ratingType != null) {
            
            deleteByProfileIdAndRatingType(profileId, ratingType);
            
        } else {
            
            deleteByProfileId(profileId);
        }
    }
    
    public void deleteByProfileId(ObjectId profileId) {
        
        ratingRepository.deleteByProfileId(profileId);
    }
    
    public void deleteByProfileIdAndUserId(ObjectId profileId, long userId) {
        
        ratingRepository.deleteByProfileIdAndUserId(profileId, userId);
    }
    
    public void deleteByProfileIdAndRatingType(ObjectId profileId, RatingType ratingType) {
        
        ratingRepository.deleteByProfileIdAndRatingType(profileId, ratingType);
    }
    
    public void deleteByProfileIdAndUserIdAndRatingType(ObjectId profileId, long userId, RatingType ratingType) {
        
        ratingRepository.deleteByProfileIdAndUserIdAndRatingType(profileId, userId, ratingType);
    }
    
    public Iterable<Rating> getByProfileId(ObjectId profileId) {
        
        return ratingRepository.findByProfileId(profileId);
    }
    
}
