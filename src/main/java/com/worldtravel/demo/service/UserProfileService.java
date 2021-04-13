package com.worldtravel.demo.service;

import com.worldtravel.demo.exception.InformationExistsException;
import com.worldtravel.demo.model.UserProfile;
import com.worldtravel.demo.repository.UserProfileRepository;
import com.worldtravel.demo.repository.UserRepository;
import com.worldtravel.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserProfileRepository(UserProfileRepository userProfileRepository){
        this.userProfileRepository= userProfileRepository;
    }

    public String createUserProfile(UserProfile userProfileObject) {
        System.out.println("service calling createUserProfile =====>");
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(myUserDetails.getUser().getUserProfile() != null){
            throw new InformationExistsException("user profile already exists");
        }
        myUserDetails.getUser().setUserProfile(userProfileObject);
        userRepository.save(myUserDetails.getUser());
        return "Profile created Successfully";
    }
}
