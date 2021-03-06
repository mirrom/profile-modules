package com.example.profile.model;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Profile extends BaseModel {
    
    @Indexed
    private String title;
    
    @Indexed
    private String description;
    
}
