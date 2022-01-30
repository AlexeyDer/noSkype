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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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
    private UserRepository userRepository;

    @Autowired
    private RegistrationController registrationController;
    /**
     * Поле для работы с mockMvc
     */
    @Autowired
    private MockMvc mockMvc;

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
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration"));
    }

    /**
     * Метод проверяет ввод коректных данных на странице регистрации
     */
    @Test
    public void addUser() throws Exception {
        this.mockMvc.perform(post("/registration")
                .with(csrf())
                .param("username", "usr")
                .param("password", "password")
                .param("confirmPassword", "password")
                .param("email", "u@u")
                .param("phone", "123"))
                .andExpect(redirectedUrl("/login"));

    }

    /**
     * Метод проверяет ввод не совпадающих паролей на странице регистрации
     */
    @Test
    public void addUserErrorPassword() throws Exception {
        this.mockMvc.perform(post("/registration")
                .with(csrf())
                .param("username", "usr2")
                .param("password", "password")
                .param("confirmPassword", "pass")
                .param("email", "u@u")
                .param("phone", "123"))
                .andExpect(model().attribute("message", "Passwords isn't equals!"))
                .andExpect(status().isOk());

    }

    /**
     * Метод проверяет, что пользователь с таким же username, который есть в бд, не может быть создан
     */
    @Test
    public void addUserErrorUserName() throws Exception {
        User user = new User();
        user.setUsername("usr3");
        userRepository.save(user);

        this.mockMvc.perform(post("/registration")
                .with(csrf())
                .param("username", "usr3")
                .param("password", "password")
                .param("confirmPassword", "password")
                .param("email", "u@u")
                .param("phone", "123"))
                .andExpect(model().attribute("message", "Person exists!"))
                .andExpect(status().isOk());
    }

}