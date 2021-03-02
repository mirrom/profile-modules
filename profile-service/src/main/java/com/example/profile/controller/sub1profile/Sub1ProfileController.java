package com.example.profile.controller.sub1profile;

import com.example.profile.dto.sub1profile.Sub1ProfileDto;
import com.example.profile.mapping.mapper.sub1profile.Sub1ProfileMapper;
import com.example.profile.model.sub1profile.Sub1Profile;
import com.example.profile.service.sub1profile.Sub1ProfileService;

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
class Sub1ProfileController implements Sub1ProfileControllerInterface {
    
    private static final Logger logger = LoggerFactory.getLogger(Sub1ProfileController.class);
    
    @Autowired
    private Sub1ProfileService sub1ProfileService;
    
    @Override
    public ResponseEntity<Sub1ProfileDto> createSub1Profile(Sub1ProfileDto sub1ProfileDto) {
        
        logger.debug("POST /v1/profiles/sub-1-profiles");
        logger.debug("{}", sub1ProfileDto);
        
        return new ResponseEntity<>(
                convertToSub1ProfileDto(sub1ProfileService.create(convertToNewSub1Profile(sub1ProfileDto))),
                HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<HttpStatus> deleteSub1Profile(String id) {
        
        logger.debug("DELETE /v1/profiles/sub-1-profiles/{}", id);
        
        if (ObjectId.isValid(id)) {
            
            sub1ProfileService.delete(new ObjectId(id));
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<Sub1ProfileDto> getSub1Profile(String id) {
        
        logger.debug("GET /v1/profiles/sub-1-profiles/{}", id);
        
        if (ObjectId.isValid(id)) {
            
            return sub1ProfileService.get(new ObjectId(id))
                    .map(sub1Profile -> new ResponseEntity<>(convertToSub1ProfileDto(sub1Profile), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<List<Sub1ProfileDto>> getSub1Profiles(int page, int size, String sortDirection, String sortBy,
            String search) {
        
        logger.debug("GET /v1/profiles/sub-1-profiles?page={}&size={}&sortDirection={}&sortBy={}&search={}", page, size,
                sortDirection, sortBy, search);
        
        List<Sub1ProfileDto> sub1ProfileDtos = new ArrayList<>();
        
        sub1ProfileService.get(page, size, sortDirection, sortBy, search)
                .forEach(sub1Profile -> sub1ProfileDtos.add(convertToSub1ProfileDto(sub1Profile)));
        
        return new ResponseEntity<>(sub1ProfileDtos, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Sub1ProfileDto> updateSub1Profile(String id, Sub1ProfileDto sub1ProfileDto) {
        
        logger.debug("PATCH /v1/profiles/sub-1-profiles/{}", id);
        logger.debug("{}", sub1ProfileDto);
        
        if (ObjectId.isValid(id)) {
            
            ObjectId objectId = new ObjectId(id);
            
            Optional<Sub1Profile> optionalSub1Profile = sub1ProfileService.get(objectId);
            
            if (optionalSub1Profile.isPresent()) {
                
                Sub1Profile sub1Profile = optionalSub1Profile.get();
                
                Sub1ProfileMapper.INSTANCE
                        .updateModel(convertToModifiedSub1Profile(sub1ProfileDto, objectId), sub1Profile);
                
                return new ResponseEntity<>(convertToSub1ProfileDto(sub1ProfileService.update(sub1Profile)),
                        HttpStatus.OK);
                
            } else {
                
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    private Sub1ProfileDto convertToSub1ProfileDto(Sub1Profile sub1Profile) {
        
        return Sub1ProfileMapper.INSTANCE.modelToDto(sub1Profile);
    }
    
    private Sub1Profile convertToNewSub1Profile(Sub1ProfileDto sub1ProfileDto) {
        
        Sub1Profile sub1Profile = Sub1ProfileMapper.INSTANCE.dtoToModel(sub1ProfileDto);
        
        sub1Profile.setId(new ObjectId());
        
        return sub1Profile;
    }
    
    private Sub1Profile convertToModifiedSub1Profile(Sub1ProfileDto sub1ProfileDto, ObjectId objectId) {
        
        Sub1Profile sub1Profile = Sub1ProfileMapper.INSTANCE.dtoToModel(sub1ProfileDto);
        
        sub1Profile.setId(objectId);
        
        return sub1Profile;
    }
    
}
