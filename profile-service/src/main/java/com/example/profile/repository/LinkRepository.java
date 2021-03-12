package com.example.profile.repository;

import com.example.profile.model.Link;
import com.example.profile.property.LinkType;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LinkRepository extends MongoRepository<Link, ObjectId> {
    
    void deleteBySourceId(ObjectId sourceId);
    
    void deleteBySourceIdAndTargetIdAndLinkType(ObjectId sourceId, ObjectId targetId, LinkType linkType);
    
    void deleteByTargetId(ObjectId targetId);
    
    List<Link> findBySourceId(ObjectId sourceId);
    
    List<Link> findBySourceIdAndLinkType(ObjectId sourceId, LinkType linkType);
    
    Optional<Link> findBySourceIdAndTargetIdAndLinkType(ObjectId sourceId, ObjectId targetId, LinkType linkType);
    
    List<Link> findByTargetId(ObjectId targetId);
    
    List<Link> findByTargetIdAndLinkType(ObjectId targetId, LinkType linkType);
    
}
