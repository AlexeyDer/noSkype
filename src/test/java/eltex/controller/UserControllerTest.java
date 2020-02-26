package eltex.controller;

import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс для тестирования контроллера административной панели
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    /**
     * Поле для работы с mockMvc
     */
    @Autowired
    private MockMvc mockMvc;
    /**
     * Поле для создания тестового обращения к бд
     */
    @MockBean
    private UserRepository userRepository;

    /**
     * Метод который не дает доступ к пользователям, кроме админа
     */
    @Test
    public void getUsersNotAuthorize() throws Exception {
        mockMvc.perform(get("/get_users").with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

    }

    /**
     * Метод тестирующий получинение списка пользователя админом
     */
    @Test
    @WithMockUser(username = "a", password = "p", authorities = "ADMIN")
    public void getUsers() throws Exception {
        List<User> users = userRepository.findAll();

        mockMvc.perform(get("/get_users").with(csrf()))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(model().attribute("users", users))
                .andExpect(MockMvcResultMatchers.view().name("user/userList"));

    }

    //    @Test
//    public void userSave() {
//    }
//
    @Autowired
    UserRepository userRepository2ForAddUsers;

//    @Test
//    @WithMockUser(username = "a", password = "p", authorities = "ADMIN")
//    public void userData() throws Exception {
//        User user = userRepository2ForAddUsers.save(new User());
//
//        mockMvc.perform(get("/get_users/1").with(csrf()))
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("/user/userEdit"));
//    }

    /**
     * Метод тестирующий вывод ошибки о том, что пользователь не найдет!
     */
    @Test
    @WithMockUser(username = "a", password = "p", authorities = "ADMIN")
    public void userErrorData() throws Exception {
        mockMvc.perform(get("/get_users/1").with(csrf()))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/errors/404"));
    }
}