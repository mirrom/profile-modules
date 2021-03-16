package com.example.profile.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;


@Data
public class ProfileRatingDto {
    
    private BigDecimal averageRating;
    
    private Long ratingsCount;
    
    private Map<String, Integer> userRatings;
    
}
