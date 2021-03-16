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
            
            ratingRepository.deleteByProfileIdAndUserIdAndRatingType(profileId, userId, ratingType);
            
        } else if (userId != null) {
            
            ratingRepository.deleteByProfileIdAndUserId(profileId, userId);
            
        } else if (ratingType != null) {
            
            ratingRepository.deleteByProfileIdAndRatingType(profileId, ratingType);
            
        } else {
            
            ratingRepository.deleteByProfileId(profileId);
        }
    }
    
    public Iterable<Rating> getByProfileId(ObjectId profileId) {
        
        return ratingRepository.findByProfileId(profileId);
    }
    
}
