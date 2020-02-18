package eltex.controller;

import eltex.entity.User;
import eltex.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс для тестирования контроллера регистрации
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {
    @Autowired
    RegistrationController registrationController;
    /**
     * Поле для работы с mockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoad() throws Exception {
        assertThat(registrationController).isNotNull();
    }

    /**
     * Метод проверяет имя атрибута, который возращается этой функцией
     */
    @Test
    public void registrationGet() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("registration"));

    }

    /*
    /**
     * Метод проверяет имя атрибута, который возращается этой функцией
     */
//    @Test
//    public void addUser() throws Exception {
//        User u = userRepository.findByUsername("usr");
//
//        if (u == null) {
//            this.mockMvc.perform(post("/registration")
//                    .param("username", "usr")
//                    .param("password", "password")
//                    .param("confirmPassword", "password")
//                    .param("email", "u@u")
//                    .param("phone", "123"))
//                    .andExpect(status().isOk());
//
//        }
//
//    }
}