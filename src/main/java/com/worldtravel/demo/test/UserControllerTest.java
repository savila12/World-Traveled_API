package com.worldtravel.demo.test;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldtravel.demo.WorledTraveledApiApplication;
import com.worldtravel.demo.controller.UserController;
import com.worldtravel.demo.model.User;
import com.worldtravel.demo.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WorledTraveledApiApplication.class)
//@WebMvcTest(value = UserController.class)
//@ContextConfiguration
//@WebAppConfiguration
public class UserControllerTest {

    @MockBean
    private UserService userService;

    String exampleUserCreation = "{\"userName\": \"Sid\", \"email \": \"sldl@sls.com\", \"password\": \"password\" }";
//    private User user;
//    private List<User> userList;
//
//
//    @InjectMocks
//    private UserController userController;


    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    //convert java object to Json String
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    //Convert Json String to Java Object
    private <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

//    @AfterEach
//    public void TearDown(){
//        user = null;
//    }

    @Test
    public void PostMappingOfUser()throws Exception{
        User mockUser = new User(3L, "Sid", "elsl@gm.com", "password");
        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(mockUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/auth/users/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleUserCreation)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

       // String inputJson = mapToJson(user);
       // MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        //int status = mvcResult.getResponse().getStatus();
        //assertEquals(201, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Product is created successfully");
        //when(userService.createUser(any())).thenReturn(user);
//        mockMvc.perform((RequestBuilder) MockServerHttpRequest.post("/auth/users/register").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
//        verify(userService, times(1)).createUser(any());
    }


}
