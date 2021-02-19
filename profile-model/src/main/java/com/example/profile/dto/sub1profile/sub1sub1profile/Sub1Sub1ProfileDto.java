package com.example.profile.dto.sub1profile.sub1sub1profile;

import com.example.profile.dto.sub1profile.Sub1ProfileDto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Sub1Sub1ProfileDto extends Sub1ProfileDto {
    
    private String sub1Sub1String;
    
    private int sub1Sub1Integer;
    
}
