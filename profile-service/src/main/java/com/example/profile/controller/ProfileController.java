package com.example.profile.controller;

import com.example.profile.dto.ProfileDto;
import com.example.profile.model.Profile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@Api("/v1/profiles")
@RestController
@RequestMapping(value = "/v1/profiles",
        produces = {"application/json"})
class ProfileController extends BaseController<Profile, ProfileDto> {
    
}
