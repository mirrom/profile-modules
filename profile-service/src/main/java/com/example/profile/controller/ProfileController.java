package com.example.profile.controller;

import com.example.profile.dto.ProfileDto;
import com.example.profile.mapper.ProfileMapper;
import com.example.profile.model.Profile;
import com.example.profile.service.ProfileService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ProfileController implements ProfileControllerInterface {
    
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    
    @Autowired
    private ProfileService profileService;
    
    @Override
    public ResponseEntity<ProfileDto> createProfile(ProfileDto profileDto) {
        
        logger.debug("POST /v1/profiles");
        logger.debug("{}", profileDto);
        
        return new ResponseEntity<>(convertToProfileDto(profileService.create(convertToNewProfile(profileDto))),
                HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<HttpStatus> deleteProfile(String id) {
        
        logger.debug("DELETE /v1/profiles/{}", id);
        
        if (ObjectId.isValid(id)) {
            
            profileService.delete(new ObjectId(id));
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<ProfileDto> getProfile(String id) {
        
        logger.debug("GET /v1/profiles/{}", id);
        
        if (ObjectId.isValid(id)) {
            
            return profileService.get(new ObjectId(id))
                    .map(profile -> new ResponseEntity<>(convertToProfileDto(profile), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<List<ProfileDto>> getProfiles(int page, int size, String sortDirection, String sortBy,
            String search) {
        
        logger.debug("GET /v1/profiles?page={}&size={}&sortDirection={}&sortBy={}&search={}", page, size, sortDirection,
                sortBy, search);
        
        List<ProfileDto> profileDtos = new ArrayList<>();
        
        profileService.get(page, size, sortDirection, sortBy, search)
                .forEach(profile -> profileDtos.add(convertToProfileDto(profile)));
        
        return new ResponseEntity<>(profileDtos, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<ProfileDto> updateProfile(String id, ProfileDto profileDto) {
        
        logger.debug("PATCH /v1/profiles/{}", id);
        logger.debug("{}", profileDto);
        
        if (ObjectId.isValid(id)) {
            
            ObjectId objectId = new ObjectId(id);
            
            Optional<Profile> optionalProfile = profileService.get(objectId);
            
            if (optionalProfile.isPresent()) {
                
                Profile profile = optionalProfile.get();
                
                ProfileMapper.INSTANCE.updateProfile(convertToModifiedProfile(profileDto, objectId), profile);
                
                return new ResponseEntity<>(convertToProfileDto(profileService.update(profile)), HttpStatus.OK);
                
            } else {
                
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    private ProfileDto convertToProfileDto(Profile profile) {
        
        return ProfileMapper.INSTANCE.profileToProfileDto(profile);
    }
    
    private Profile convertToNewProfile(ProfileDto profileDto) {
        
        Profile profile = ProfileMapper.INSTANCE.profileDtoToProfile(profileDto);
        
        profile.setId(new ObjectId());
        profile.setCreatedAt(LocalDateTime.now());
        profile.setModifiedAt(profile.getCreatedAt());
        
        return profile;
    }
    
    private Profile convertToModifiedProfile(ProfileDto profileDto, ObjectId objectId) {
        
        Profile profile = ProfileMapper.INSTANCE.profileDtoToProfile(profileDto);
        
        profile.setId(objectId);
        profile.setModifiedAt(LocalDateTime.now());
        
        return profile;
    }
    
}
