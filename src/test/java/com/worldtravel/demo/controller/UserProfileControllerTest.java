package com.worldtravel.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldtravel.demo.model.User;
import com.worldtravel.demo.model.UserProfile;
import com.worldtravel.demo.security.MyUserDetails;
import com.worldtravel.demo.security.MyUserDetailsService;
import com.worldtravel.demo.service.UserProfileService;
import com.worldtravel.demo.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserProfileControllerTest {

    @MockBean
    private UserProfileService userProfileService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    private UserProfile userProfile;

    @InjectMocks
    private UserProfileController userProfileController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    public void setUp(){
        userProfile = new UserProfile(2L, "Sidney", "Avila", "My profile");
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    public void tearDown(){
        userProfile = null;
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    @WithMockUser("Sidney")
    public void createUserProfileTest() throws Exception{
        //ResponseEntity<HashMap> response = new ResponseEntity<>(HttpStatus.OK);
        //MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String response = "Profile created Successfully";
//        when(userProfileService.createUserProfile(any())).thenReturn(response);
//        mockMvc.perform(post("/auth/api/profile")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapToJson(userProfile)))
//                .andExpect(status().isOk());

        ResponseEntity<String> result = template.withBasicAuth("Sidney", "password")
                .getForEntity("/auth/api/profile", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}
