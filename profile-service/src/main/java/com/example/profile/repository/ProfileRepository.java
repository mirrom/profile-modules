package com.example.profile.repository;

import com.example.profile.model.Profile;
import com.example.profile.model.QProfile;

import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends BaseRepository<Profile, QProfile> {
    
}
