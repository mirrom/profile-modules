package com.example.profile.controller;

import com.example.profile.dto.BaseDto;
import com.example.profile.dto.LinkDto;
import com.example.profile.mapper.BaseMapper;
import com.example.profile.mapper.LinkMapper;
import com.example.profile.model.BaseModel;
import com.example.profile.property.LinkType;
import com.example.profile.service.BaseServiceInterface;
import com.example.profile.service.LinkService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public abstract class BaseController<M extends BaseModel, D extends BaseDto> implements BaseControllerInterface<D> {
    
    @Autowired
    private BaseMapper<M, D> mapper;
    
    @Autowired
    private LinkMapper linkMapper;
    
    @Autowired
    private BaseServiceInterface<M> service;
    
    @Autowired
    private LinkService linkService;
    
    public ResponseEntity<D> create(D dto) {
        
        return new ResponseEntity<>(mapper.modelToDto(service.create(mapper.dtoToModel(dto))), HttpStatus.CREATED);
    }
    
    public ResponseEntity<HttpStatus> delete(String id) {
        
        if (ObjectId.isValid(id)) {
            
            service.delete(new ObjectId(id));
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<D> get(String id) {
        
        if (ObjectId.isValid(id)) {
            
            return service.get(new ObjectId(id))
                    .map(model -> new ResponseEntity<>(mapper.modelToDto(model), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    public ResponseEntity<List<D>> get(int page, int size, String sortDirection, String sortBy, String search) {
        
        List<D> dtos = new ArrayList<>();
        
        service.get(page, size, sortDirection, sortBy, search).forEach(model -> dtos.add(mapper.modelToDto(model)));
        
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    public ResponseEntity<D> update(String id, D dto) {
        
        if (ObjectId.isValid(id)) {
            
            ObjectId objectId = new ObjectId(id);
            
            Optional<M> optionalModel = service.get(objectId);
            
            if (optionalModel.isPresent()) {
                
                M model = optionalModel.get();
                
                mapper.updateModel(mapper.dtoToModel(dto, objectId), model);
                
                return new ResponseEntity<>(mapper.modelToDto(service.update(model)), HttpStatus.OK);
                
            } else {
                
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<LinkDto> createLink(String sourceId, String targetId, LinkType linkType) {
        
        if (ObjectId.isValid(sourceId) && ObjectId.isValid(targetId)) {
            
            return new ResponseEntity<>(
                    linkMapper.modelToDto(linkService.create(new ObjectId(sourceId), new ObjectId(targetId), linkType)),
                    HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<HttpStatus> deleteLink(String sourceId, String targetId, LinkType linkType) {
        
        if (ObjectId.isValid(sourceId) && ObjectId.isValid(targetId)) {
            
            linkService.delete(new ObjectId(sourceId), new ObjectId(targetId), linkType);
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, List<LinkDto>>> getLinks(String sourceId, LinkType linkType) {
        
        if (ObjectId.isValid(sourceId)) {
            
            Map<String, List<LinkDto>> stringLinkDtosMap = new HashMap<>();
            
            List<LinkDto> linkDtos = new ArrayList<>();
            
            linkService.getBySourceIdAndLinkType(new ObjectId(sourceId), linkType)
                    .forEach(link -> linkDtos.add(linkMapper.modelToDto(link)));
            
            List<LinkType> linkTypes = new ArrayList<>();
            
            linkDtos.forEach(linkDto -> linkTypes.add(linkDto.getLinkType()));
            
            linkTypes.stream().distinct().forEach(linkTypee -> stringLinkDtosMap.put(linkTypee.name(),
                    linkDtos.stream().filter(linkDto -> linkDto.getLinkType() == linkTypee)
                            .collect(Collectors.toList())));
            
            return new ResponseEntity<>(stringLinkDtosMap, HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<LinkDto> createBacklink(String targetId, String sourceId, LinkType linkType) {
        
        if (ObjectId.isValid(sourceId) && ObjectId.isValid(targetId)) {
            
            return new ResponseEntity<>(
                    linkMapper.modelToDto(linkService.create(new ObjectId(sourceId), new ObjectId(targetId), linkType)),
                    HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<HttpStatus> deleteBacklink(String targetId, String sourceId, LinkType linkType) {
        
        if (ObjectId.isValid(sourceId) && ObjectId.isValid(targetId)) {
            
            linkService.delete(new ObjectId(sourceId), new ObjectId(targetId), linkType);
            
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, List<LinkDto>>> getBacklinks(String targetId, LinkType linkType) {
        
        if (ObjectId.isValid(targetId)) {
            
            Map<String, List<LinkDto>> stringLinkDtosMap = new HashMap<>();
            
            List<LinkDto> linkDtos = new ArrayList<>();
            
            linkService.getByTargetIdAndLinkType(new ObjectId(targetId), linkType)
                    .forEach(link -> linkDtos.add(linkMapper.modelToDto(link)));
            
            List<LinkType> linkTypes = new ArrayList<>();
            
            linkDtos.forEach(linkDto -> linkTypes.add(linkDto.getLinkType()));
            
            linkTypes.stream().distinct().forEach(linkTypee -> stringLinkDtosMap.put(linkTypee.name(),
                    linkDtos.stream().filter(linkDto -> linkDto.getLinkType() == linkTypee)
                            .collect(Collectors.toList())));
            
            return new ResponseEntity<>(stringLinkDtosMap, HttpStatus.OK);
            
        } else {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
