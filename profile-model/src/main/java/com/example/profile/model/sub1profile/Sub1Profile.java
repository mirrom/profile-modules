package com.example.profile.model.sub1profile;

import com.example.profile.model.Profile;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Sub1Profile extends Profile {
    
    private String sub1String;
    
    private Integer sub1Integer;
    
}
