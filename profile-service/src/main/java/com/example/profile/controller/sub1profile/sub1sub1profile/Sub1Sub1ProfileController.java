package com.example.profile.controller.sub1profile.sub1sub1profile;

import com.example.profile.dto.sub1profile.sub1sub1profile.Sub1Sub1ProfileDto;
import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;
import com.example.profile.service.sub1profile.sub1sub1profile.Sub1Sub1ProfileService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
class Sub1Sub1ProfileController implements Sub1Sub1ProfileControllerInterface {
    
    private static final Logger logger = LoggerFactory.getLogger(Sub1Sub1ProfileController.class);
    
    @Autowired
    private Sub1Sub1ProfileService sub1Sub1ProfileService;
    
    @Autowired
    private ModelMapper dtoModelMapper;
    
    @Autowired
    private ModelMapper pojoModelMapper;
    
    @Override
    public ResponseEntity<Sub1Sub1ProfileDto> createSub1Sub1Profile(Sub1Sub1ProfileDto sub1Sub1ProfileDto) {
        
        logger.debug("POST /v1/profiles/sub-1-profiles/sub-1-sub-1-profiles");
        logger.debug("{}", sub1Sub1ProfileDto);
        
        return new ResponseEntity<>(convertToSub1Sub1ProfileDto(
                sub1Sub1ProfileService.create(convertToNewSub1Sub1Profile(sub1Sub1ProfileDto))), HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<HttpStatus> deleteSub1Sub1Profile(String id) {
        
        logger.debug("DELETE /v1/profiles/sub-1-profiles/sub-1-sub-1-profiles/{}", id);
        
        if (ObjectId.isValid(id)) {
            
            sub1Sub1ProfileService.delete(new ObjectId(id));
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<Sub1Sub1ProfileDto> getSub1Sub1Profile(String id) {
        
        logger.debug("GET /v1/profiles/sub-1-profiles/sub-1-sub-1-profiles/{}", id);
        
        if (ObjectId.isValid(id)) {
            
            return sub1Sub1ProfileService.get(new ObjectId(id))
                    .map(sub1Sub1Profile -> new ResponseEntity<>(convertToSub1Sub1ProfileDto(sub1Sub1Profile),
                            HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<List<Sub1Sub1ProfileDto>> getSub1Sub1Profiles(int page, int size, String sortDirection,
            String sortBy) {
        
        logger.debug("GET /v1/profiles/sub-1-profiles/sub-1-sub-1-profiles?page={}&size={}&sortDirection={}&sortBy={}",
                page, size, sortDirection, sortBy);
        
        List<Sub1Sub1ProfileDto> sub1Sub1ProfileDtos = new ArrayList<>();
        
        sub1Sub1ProfileService.get(page, size, sortDirection, sortBy)
                .forEach(sub1Sub1Profile -> sub1Sub1ProfileDtos.add(convertToSub1Sub1ProfileDto(sub1Sub1Profile)));
        
        return new ResponseEntity<>(sub1Sub1ProfileDtos, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Sub1Sub1ProfileDto> updateSub1Sub1Profile(String id, Sub1Sub1ProfileDto sub1Sub1ProfileDto) {
        
        logger.debug("PUT /v1/profiles/sub-1-profiles/sub-1-sub-1-profiles/{}", id);
        logger.debug("{}", sub1Sub1ProfileDto);
        
        if (ObjectId.isValid(id)) {
            
            ObjectId objectId = new ObjectId(id);
            
            Optional<Sub1Sub1Profile> optionalSub1Sub1Profile = sub1Sub1ProfileService.get(objectId);
            
            if (optionalSub1Sub1Profile.isPresent()) {
                
                Sub1Sub1Profile sub1Sub1Profile = optionalSub1Sub1Profile.get();
                
                pojoModelMapper.map(convertToModifiedSub1Sub1Profile(sub1Sub1ProfileDto, objectId), sub1Sub1Profile);
                
                return new ResponseEntity<>(convertToSub1Sub1ProfileDto(sub1Sub1ProfileService.update(sub1Sub1Profile)),
                        HttpStatus.OK);
                
            } else {
                
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    private Sub1Sub1ProfileDto convertToSub1Sub1ProfileDto(Sub1Sub1Profile sub1Sub1Profile) {
        
        return dtoModelMapper.map(sub1Sub1Profile, Sub1Sub1ProfileDto.class);
    }
    
    private Sub1Sub1Profile convertToNewSub1Sub1Profile(Sub1Sub1ProfileDto sub1Sub1ProfileDto) {
        
        Sub1Sub1Profile sub1Sub1Profile = dtoModelMapper.map(sub1Sub1ProfileDto, Sub1Sub1Profile.class);
        
        sub1Sub1Profile.setId(new ObjectId());
        sub1Sub1Profile.setCreatedAt(LocalDateTime.now());
        sub1Sub1Profile.setModifiedAt(sub1Sub1Profile.getCreatedAt());
        
        return sub1Sub1Profile;
    }
    
    private Sub1Sub1Profile convertToModifiedSub1Sub1Profile(Sub1Sub1ProfileDto sub1Sub1ProfileDto, ObjectId objectId) {
        
        Sub1Sub1Profile sub1Sub1Profile = dtoModelMapper.map(sub1Sub1ProfileDto, Sub1Sub1Profile.class);
        
        sub1Sub1Profile.setId(objectId);
        sub1Sub1Profile.setModifiedAt(LocalDateTime.now());
        
        return sub1Sub1Profile;
    }
    
}
