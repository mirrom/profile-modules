package com.example.profile.service;

import com.example.profile.model.BaseModel;

import java.util.Optional;

import org.bson.types.ObjectId;


public interface BaseServiceInterface<M extends BaseModel> {
    
    M create(M model);
    
    void delete(ObjectId profileId);
    
    Iterable<M> get(int page, int size, String sortDirection, String sortBy, String search);
    
    Optional<M> get(ObjectId profileId);
    
    M update(M model);
    
}
