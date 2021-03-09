package com.example.profile.property;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public enum Type {
    
    @ApiModelProperty("Profile") PROFILE,
    @ApiModelProperty("Sub 1 Profile") SUB_1_PROFILE,
    @ApiModelProperty("Sub 1 Sub 1 Profile") SUB_1_SUB_1_PROFILE,
    
}
