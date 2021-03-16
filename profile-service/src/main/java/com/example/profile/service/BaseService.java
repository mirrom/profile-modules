package com.example.profile.service;

import com.example.profile.model.BaseModel;
import com.example.profile.repository.BaseRepository;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


public abstract class BaseService<M extends BaseModel, Q extends EntityPath<?>> implements BaseServiceInterface<M> {
    
    @Autowired
    LinkService linkService;
    
    @Autowired
    RatingService ratingService;
    
    protected BaseRepository<M, Q> repository;
    
    protected BaseService(BaseRepository<M, Q> repository) {
        
        this.repository = repository;
    }
    
    @Override
    public M create(M model) {
        
        return repository.save(model);
    }
    
    @Override
    public void delete(ObjectId objectId) {
        
        repository.deleteById(objectId);
        linkService.deleteBySourceId(objectId);
        linkService.deleteByTargetId(objectId);
        ratingService.deleteByProfileId(objectId);
    }
    
    public Iterable<M> get(int page, int size, String sortDirection, String sortBy,
            BooleanExpression booleanExpression) {
        
        return repository.findAll(booleanExpression,
                PageRequest.of(page, size, Sort.Direction.fromString(sortDirection), sortBy));
    }
    
    @Override
    public Optional<M> get(ObjectId objectId) {
        
        return repository.findById(objectId);
    }
    
    @Override
    public M update(M model) {
        
        return repository.save(model);
    }
    
}
