package com.example.profile.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;


public interface Serviceable<T> {
    
    T create(T profile);
    
    void delete(ObjectId objectId);
    
    Iterable<T> get(int page, int size, String sortDirection, String sortBy);
    
    Optional<T> get(ObjectId objectId);
    
    T update(T profile);
    
}
