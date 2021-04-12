package com.worldtravel.demo.security;

import com.worldtravel.demo.model.User;
import com.worldtravel.demo.repository.UserRepository;
import com.worldtravel.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        return new MyUserDetails(user);
    }
}
