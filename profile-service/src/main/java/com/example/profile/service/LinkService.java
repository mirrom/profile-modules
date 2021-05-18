package com.example.profile.service;

import com.example.profile.model.Link;
import com.example.profile.property.LinkType;
import com.example.profile.repository.LinkRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LinkService {
    
    @Autowired
    LinkRepository linkRepository;
    
    public Link create(ObjectId sourceId, ObjectId targetId, LinkType linkType) {
        
        var optionalLink = linkRepository.findBySourceIdAndTargetIdAndLinkType(sourceId, targetId, linkType);
        
        return optionalLink.orElseGet(() -> {
            
            var link = new Link();
            
            link.setId(new ObjectId());
            link.setSourceId(sourceId);
            link.setTargetId(targetId);
            link.setLinkType(linkType);
            
            return linkRepository.save(link);
        });
    }
    
    public void delete(ObjectId sourceId, ObjectId targetId, LinkType linkType) {
        
        linkRepository.deleteBySourceIdAndTargetIdAndLinkType(sourceId, targetId, linkType);
    }
    
    public void deleteBySourceId(ObjectId sourceId) {
        
        linkRepository.deleteBySourceId(sourceId);
    }
    
    public void deleteByTargetId(ObjectId targetId) {
        
        linkRepository.deleteByTargetId(targetId);
    }
    
    public Iterable<Link> getBySourceIdAndLinkType(ObjectId sourceId, LinkType linkType) {
        
        if (linkType == null) {
            
            return linkRepository.findBySourceId(sourceId);
            
        } else {
            
            return linkRepository.findBySourceIdAndLinkType(sourceId, linkType);
        }
        
    }
    
    public Iterable<Link> getByTargetIdAndLinkType(ObjectId targetId, LinkType linkType) {
        
        if (linkType == null) {
            
            return linkRepository.findByTargetId(targetId);
            
        } else {
            
            return linkRepository.findByTargetIdAndLinkType(targetId, linkType);
        }
        
    }
    
}
