package com.worldtravel.demo.test;


import com.worldtravel.demo.controller.UserController;
import com.worldtravel.demo.model.User;
import com.worldtravel.demo.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserService userService;
    private User user;
    private List<User> userList;


    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp(){
        user = new User(1L, "Sidney", "asdf@dlfk.com", "password");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    public void TearDown(){
        user = null;
    }

    @Test
    public void PostMappingOfUser()throws Exception{
        when(userService.createUser(any())).thenReturn(user);
        mockMvc.perform((RequestBuilder) MockServerHttpRequest.post("/auth/users/register").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        verify(userService, times(1)).createUser(any());
    }


}
