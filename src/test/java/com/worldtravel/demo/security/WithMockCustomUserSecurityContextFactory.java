package com.worldtravel.demo.security;

import com.worldtravel.demo.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext  createSecurityContext(WithMockCustomUser customUser) {
        User user = new User(3L, customUser.userName(), "jslkl@sl.com", customUser.password());
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        MyUserDetails principal = new MyUserDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null,null);
        context.setAuthentication(authentication);
        return context;
    }

}
