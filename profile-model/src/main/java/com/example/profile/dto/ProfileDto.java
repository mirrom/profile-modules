package com.example.profile.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ProfileDto {
    
    private String id;
    
    @ApiModelProperty(required = true)
    private String title;
    
    private String description;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime modifiedAt;
    
}
