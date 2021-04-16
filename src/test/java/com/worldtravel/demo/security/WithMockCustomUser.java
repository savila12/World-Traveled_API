package com.worldtravel.demo.security;

import com.worldtravel.demo.controller.UserController;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    String userName();
    String email();
    //String password();
}
