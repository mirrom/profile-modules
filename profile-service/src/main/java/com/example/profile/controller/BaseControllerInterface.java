package com.example.profile.controller;

import com.example.profile.dto.BaseDto;
import com.example.profile.dto.LinkDto;
import com.example.profile.dto.ProfileRatingDto;
import com.example.profile.dto.RatingDto;
import com.example.profile.property.LinkType;
import com.example.profile.property.RatingType;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


interface BaseControllerInterface<D extends BaseDto> {
    
    @PostMapping
    ResponseEntity<D> create(@RequestBody D dto);
    
    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable String id);
    
    @GetMapping("/{id}")
    ResponseEntity<D> get(@PathVariable String id);
    
    @GetMapping
    ResponseEntity<List<D>> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "title") String sortBy, @RequestParam(defaultValue = "") String search);
    
    @PatchMapping("/{id}")
    ResponseEntity<D> update(@PathVariable String id, @RequestBody D dto);
    
    @PostMapping("/{id}/links")
    ResponseEntity<LinkDto> createLink(@PathVariable(name = "id") String sourceId, @RequestParam String targetId,
            @RequestParam LinkType linkType);
    
    @DeleteMapping("/{id}/links")
    ResponseEntity<HttpStatus> deleteLink(@PathVariable(name = "id") String sourceId, @RequestParam String targetId,
            @RequestParam LinkType linkType);
    
    @GetMapping("/{id}/links")
    ResponseEntity<Map<String, List<LinkDto>>> getLinks(@PathVariable(name = "id") String sourceId,
            @RequestParam(required = false) LinkType linkType);
    
    @PostMapping("/{id}/backlinks")
    ResponseEntity<LinkDto> createBacklink(@PathVariable(name = "id") String targetId, @RequestParam String sourceId,
            @RequestParam LinkType linkType);
    
    @DeleteMapping("/{id}/backlinks")
    ResponseEntity<HttpStatus> deleteBacklink(@PathVariable(name = "id") String targetId, @RequestParam String sourceId,
            @RequestParam LinkType linkType);
    
    @GetMapping("/{id}/backlinks")
    ResponseEntity<Map<String, List<LinkDto>>> getBacklinks(@PathVariable(name = "id") String targetId,
            @RequestParam(required = false) LinkType linkType);
    
    @PostMapping("/{id}/ratings")
    ResponseEntity<RatingDto> createOrUpdateRating(@PathVariable(name = "id") String profileId,
            @RequestParam Long userId, @RequestParam RatingType ratingType, @RequestParam Integer userRating);
    
    @DeleteMapping("/{id}/ratings")
    ResponseEntity<HttpStatus> deleteRatings(@PathVariable(name = "id") String profileId,
            @RequestParam(required = false) Long userId, @RequestParam(required = false) RatingType ratingType);
    
    @GetMapping("/{id}/ratings")
    ResponseEntity<Map<String, ProfileRatingDto>> getRatings(@PathVariable(name = "id") String profileId);
    
}
