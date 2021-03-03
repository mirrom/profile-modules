package com.example.profile.controller.sub1profile;

import com.example.profile.dto.sub1profile.Sub1ProfileDto;
import com.example.profile.mapping.mapper.sub1profile.Sub1ProfileMapper;
import com.example.profile.model.sub1profile.Sub1Profile;
import com.example.profile.service.sub1profile.Sub1ProfileService;

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
    private Sub1ProfileMapper mapper;
    
    @Autowired
    private Sub1ProfileService service;
    
    @Override
    public ResponseEntity<Sub1ProfileDto> createSub1Profile(Sub1ProfileDto sub1ProfileDto) {
        
        logger.debug("POST /v1/profiles/sub-1-profiles");
        logger.debug("{}", sub1ProfileDto);
        
        return new ResponseEntity<>(mapper.modelToDto(service.create(mapper.dtoToModel(sub1ProfileDto))),
                HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<HttpStatus> deleteSub1Profile(String id) {
        
        logger.debug("DELETE /v1/profiles/sub-1-profiles/{}", id);
        
        if (ObjectId.isValid(id)) {
            
            service.delete(new ObjectId(id));
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<Sub1ProfileDto> getSub1Profile(String id) {
        
        logger.debug("GET /v1/profiles/sub-1-profiles/{}", id);
        
        if (ObjectId.isValid(id)) {
            
            return service.get(new ObjectId(id))
                    .map(sub1Profile -> new ResponseEntity<>(mapper.modelToDto(sub1Profile), HttpStatus.OK))
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
        
        service.get(page, size, sortDirection, sortBy, search)
                .forEach(sub1Profile -> sub1ProfileDtos.add(mapper.modelToDto(sub1Profile)));
        
        return new ResponseEntity<>(sub1ProfileDtos, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<Sub1ProfileDto> updateSub1Profile(String id, Sub1ProfileDto sub1ProfileDto) {
        
        logger.debug("PATCH /v1/profiles/sub-1-profiles/{}", id);
        logger.debug("{}", sub1ProfileDto);
        
        if (ObjectId.isValid(id)) {
            
            ObjectId objectId = new ObjectId(id);
            
            Optional<Sub1Profile> optionalSub1Profile = service.get(objectId);
            
            if (optionalSub1Profile.isPresent()) {
                
                Sub1Profile sub1Profile = optionalSub1Profile.get();
                
                mapper.updateModel(mapper.dtoToModel(sub1ProfileDto, objectId), sub1Profile);
                
                return new ResponseEntity<>(mapper.modelToDto(service.update(sub1Profile)), HttpStatus.OK);
                
            } else {
                
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
