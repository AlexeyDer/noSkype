package eltex.controller;

import eltex.entity.Role;
import eltex.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collection;
import java.util.Collections;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private UserRepository userRepository;
    /**
     * Поле для работы с mockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsersNotAuthorize() throws Exception {
        mockMvc.perform(get("/get_users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

    }

//    @Test
//    @WithMockUser(roles = "ADMIN")
//    public void getUsers() throws Exception {
////        mockMvc.perform(get("/get_users"))
////                .andDo(print())
////                .andExpect(authenticated().withRoles("ADMIN"))
////                .andExpect(MockMvcResultMatchers.view().name("user/userList"));
//
//    }

//    @Test
//    public void userSave() {
//    }
//
//    @Test
//    public void userData() {
//    }
}