package com.back.city.controller;

import com.back.city.dto.user.UserProfileDTO;
import com.back.city.services.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile/{id}")
    public UserProfileDTO getProfileData(@PathVariable Long id){
        return profileService.getUserProfile(id);
    }

}
