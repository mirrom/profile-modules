package com.example.profile.dto.sub1profile;

import com.example.profile.dto.ProfileDto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Sub1ProfileDto extends ProfileDto {
    
    private String sub1String;
    
    private int sub1Integer;
    
}
