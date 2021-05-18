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
    
    @GetMapping
    ResponseEntity<List<D>> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "title") String sortBy, @RequestParam(defaultValue = "") String search);
    
    @PostMapping
    ResponseEntity<D> create(@RequestBody D dto);
    
    @GetMapping("/{profileId}")
    ResponseEntity<D> get(@PathVariable String profileId);
    
    @PatchMapping("/{profileId}")
    ResponseEntity<D> update(@PathVariable String profileId, @RequestBody D dto);
    
    @DeleteMapping("/{profileId}")
    ResponseEntity<HttpStatus> delete(@PathVariable String profileId);
    
    @GetMapping("/{profileId}/links")
    ResponseEntity<Map<String, List<LinkDto>>> getLinks(@PathVariable(name = "profileId") String sourceId,
            @RequestParam(required = false) LinkType linkType);
    
    @PostMapping("/{profileId}/links/{targetId}")
    ResponseEntity<LinkDto> createLink(@PathVariable(name = "profileId") String sourceId, @PathVariable String targetId,
            @RequestParam LinkType linkType);
    
    @DeleteMapping("/{profileId}/links/{targetId}")
    ResponseEntity<HttpStatus> deleteLink(@PathVariable(name = "profileId") String sourceId,
            @PathVariable String targetId, @RequestParam LinkType linkType);
    
    @GetMapping("/{profileId}/backlinks")
    ResponseEntity<Map<String, List<LinkDto>>> getBacklinks(@PathVariable(name = "profileId") String targetId,
            @RequestParam(required = false) LinkType linkType);
    
    @PostMapping("/{profileId}/backlinks/{sourceId}")
    ResponseEntity<LinkDto> createBacklink(@PathVariable(name = "profileId") String targetId,
            @PathVariable String sourceId, @RequestParam LinkType linkType);
    
    @DeleteMapping("/{profileId}/backlinks/{sourceId}")
    ResponseEntity<HttpStatus> deleteBacklink(@PathVariable(name = "profileId") String targetId,
            @PathVariable String sourceId, @RequestParam LinkType linkType);
    
    @GetMapping("/{profileId}/ratings")
    ResponseEntity<Map<String, ProfileRatingDto>> getRatings(@PathVariable String profileId);
    
    @PostMapping("/{profileId}/ratings")
    ResponseEntity<RatingDto> createOrUpdateRating(@PathVariable String profileId, @RequestParam Long userId,
            @RequestParam RatingType ratingType, @RequestParam Integer userRating);
    
    @DeleteMapping("/{profileId}/ratings")
    ResponseEntity<HttpStatus> deleteRatings(@PathVariable String profileId,
            @RequestParam(required = false) Long userId, @RequestParam(required = false) RatingType ratingType);
    
}
