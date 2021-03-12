package com.example.profile.dto;

import com.example.profile.property.LinkType;

import lombok.Data;


@Data
public class LinkDto {
    
    private String sourceId;
    
    private String sourceTitle;
    
    private String targetId;
    
    private String targetTitle;
    
    private LinkType linkType;
    
}
