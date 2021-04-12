package com.worldtravel.demo.service;

import com.worldtravel.demo.exception.InformationExistsException;
import com.worldtravel.demo.model.User;
import com.worldtravel.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

   @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User userObject){
        System.out.println("service calling createUser");

        if (!userRepository.existsByEmail(userObject.getEmail())){
            //userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistsException("user with email address " + userObject.getEmail() + " already exists.");
        }
    }
}
