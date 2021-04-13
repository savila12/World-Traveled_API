package com.worldtravel.demo.controller;

import com.worldtravel.demo.model.UserProfile;
import com.worldtravel.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/auth/api")
public class UserProfileController {
    private UserProfileService userProfileService;

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @PostMapping("/profile")
    public ResponseEntity<HashMap> createUserProfile(@RequestBody UserProfile userProfileObject) {
        System.out.println("calling createUserProfile =====>");
        String status = userProfileService.createUserProfile(userProfileObject);
        HashMap message = new HashMap();
        message.put("message", status);
        return new ResponseEntity<HashMap>(message, HttpStatus.OK);
    }
}
