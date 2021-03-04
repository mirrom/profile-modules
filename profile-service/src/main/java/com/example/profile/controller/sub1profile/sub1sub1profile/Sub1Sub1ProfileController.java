package com.example.profile.controller.sub1profile.sub1sub1profile;

import com.example.profile.controller.BaseController;
import com.example.profile.dto.sub1profile.sub1sub1profile.Sub1Sub1ProfileDto;
import com.example.profile.model.sub1profile.sub1sub1profile.Sub1Sub1Profile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@Api("/v1/profiles/sub-1-profiles/sub-1-sub-1-profiles")
@RestController
@RequestMapping(value = "/v1/profiles/sub-1-profiles/sub-1-sub-1-profiles",
        produces = {"application/json"})
class Sub1Sub1ProfileController extends BaseController<Sub1Sub1Profile, Sub1Sub1ProfileDto> {

}
