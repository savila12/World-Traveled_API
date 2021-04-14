package com.worldtravel.demo.service;

import com.worldtravel.demo.exception.InformationExistsException;
import com.worldtravel.demo.exception.InformationNotFoundException;
import com.worldtravel.demo.model.User;
import com.worldtravel.demo.model.loginRequest.LoginRequest;
import com.worldtravel.demo.model.response.LoginResponse;
import com.worldtravel.demo.repository.UserRepository;
import com.worldtravel.demo.security.JWTUtils;
import com.worldtravel.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User createUser(User userObject){
        System.out.println("service calling createUser");

        if (!userRepository.existsByEmail(userObject.getEmail())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistsException("user with email address " + userObject.getEmail() + " already exists.");
        }
    }

    public ResponseEntity<Object> loginUser(LoginRequest loginRequest){
        System.out.println("service calling loginUser =====>");
        try{
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            final String JWT = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));

        }catch(NullPointerException e){
            throw new InformationNotFoundException("user with email address " + loginRequest.getEmail() + " not found");
        }
    }

    public String updatePassword(String newPassword){
        System.out.println("calling updatePassword ==>");
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findUserByEmail(myUserDetails.getUser().getEmail());
        if (currentUser != null){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(currentUser);
            return "Password successfully updated.";
        }
        return "Error";
    }
}
