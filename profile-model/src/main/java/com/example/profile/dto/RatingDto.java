package com.example.profile.dto;

import lombok.Data;


@Data
public class RatingDto {
    
    private String profileId;
    
    private Long userId;
    
    private String ratingType;
    
    private String userRating;
    
}
