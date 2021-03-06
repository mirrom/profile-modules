package com.example.profile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class ProfileDto extends BaseDto {
    
    @ApiModelProperty(required = true)
    private String title;
    
    private String description;
    
}
