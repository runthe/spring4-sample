package com.soo.controller;

import com.soo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author KHS
 */
public class UserControllerTest {

    UserController userController;
    MockMvc mockMvc;
    UserRepository mockRepository;

    @Before
    public void setup() {
        userController = new UserController();
        mockRepository = mock(UserRepository.class);
        userController.userRepository = mockRepository;
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void postUser() throws Exception {
        mockMvc.perform(post("/api/users")
                .param("name", "test")
                .param("password", "test"))
                .andExpect(status().isCreated());
    }

}