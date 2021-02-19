package com.example.profile.repository.sub1profile.sub1sub1profile;


import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Sub1Sub1ProfileRepository extends MongoRepository<Sub1Sub1Profile, ObjectId> {
    
}
