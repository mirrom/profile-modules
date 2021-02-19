package com.example.profile.controller.sub1profile.sub1sub1profile;

import com.example.profile.dto.sub1profile.sub1sub1profile.Sub1Sub1ProfileDto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;


@Api("/v1/profiles/sub-1-profiles/sub-1-sub-1-profiles")
@RequestMapping(value = "/v1/profiles/sub-1-profiles/sub-1-sub-1-profiles",
        produces = {"application/json"})
interface Sub1Sub1ProfileControllerInterface {
    
    @PostMapping
    public ResponseEntity<Sub1Sub1ProfileDto> createSub1Sub1Profile(@RequestBody Sub1Sub1ProfileDto sub1Sub1ProfileDto);
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSub1Sub1Profile(@PathVariable String id);
    
    @GetMapping("/{id}")
    public ResponseEntity<Sub1Sub1ProfileDto> getSub1Sub1Profile(@PathVariable String id);
    
    @GetMapping
    public ResponseEntity<List<Sub1Sub1ProfileDto>> getSub1Sub1Profiles(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size, @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "title") String sortBy);
    
    @PatchMapping("/{id}")
    public ResponseEntity<Sub1Sub1ProfileDto> updateSub1Sub1Profile(@PathVariable String id,
            @RequestBody Sub1Sub1ProfileDto sub1Sub1ProfileDto);
    
}
