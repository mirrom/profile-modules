package com.example.profile.model.sub1profile.sub1sub1profile;

import com.example.profile.model.sub1profile.Sub1Profile;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Sub1Sub1Profile extends Sub1Profile {
    
    private String sub1Sub1String;
    
    private Integer sub1Sub1Integer;
    
}
