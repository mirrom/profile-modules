package com.example.profile.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


interface BaseControllerInterface<D> {
    
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
    
}
