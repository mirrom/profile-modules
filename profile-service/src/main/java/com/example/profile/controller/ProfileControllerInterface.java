package com.example.profile.controller;

import com.example.profile.dto.ProfileDto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;


@Api("/v1/profiles")
@RequestMapping(value = "/v1/profiles",
        produces = {"application/json"})
interface ProfileControllerInterface {
    
    @PostMapping
    ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto);
    
    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteProfile(@PathVariable String id);
    
    @GetMapping("/{id}")
    ResponseEntity<ProfileDto> getProfile(@PathVariable String id);
    
    @GetMapping
    ResponseEntity<List<ProfileDto>> getProfiles(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "title") String sortBy);
    
    @PatchMapping("/{id}")
    ResponseEntity<ProfileDto> updateProfile(@PathVariable String id, @RequestBody ProfileDto profileDto);
    
}
