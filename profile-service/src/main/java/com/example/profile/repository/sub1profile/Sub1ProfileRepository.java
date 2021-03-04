package com.example.profile.repository.sub1profile;

import com.example.profile.model.sub1profile.QSub1Profile;
import com.example.profile.model.sub1profile.Sub1Profile;
import com.example.profile.repository.BaseRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface Sub1ProfileRepository extends BaseRepository<Sub1Profile, QSub1Profile> {
    
}
