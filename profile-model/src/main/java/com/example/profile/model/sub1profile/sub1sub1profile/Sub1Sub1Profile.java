package com.example.profile.model.sub1profile.sub1sub1profile;

import com.example.profile.model.sub1profile.Sub1Profile;
import com.example.profile.property.Type;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Sub1Sub1Profile extends Sub1Profile {
    
    private String sub1Sub1String;
    
    private Integer sub1Sub1Integer;
    
    public Sub1Sub1Profile() {
        
        super();
        
        setType(Type.SUB_1_SUB_1_PROFILE);
    }
    
}
