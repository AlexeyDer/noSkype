package eltex.controller;

import eltex.entity.User;
import eltex.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
@TestPropertySource("/application.yml")
@Sql(value = {"/create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
//    @WithMockUser(username = "a", password = "p", authorities = "ADMIN")
//    public void userSave() throws Exception {
//        User user = userRepository.findById(Long.valueOf(1));
//        mockMvc.perform(post("/get_users/{id}", 1).with(csrf())
//                .param("username", user.getUsername())
//                .param("userId", user.getId().toString())
//        )
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(username = "a", password = "p", authorities = "ADMIN")
    public void userData() throws Exception {
        User user = userRepository.findById(Long.valueOf(1));

        mockMvc.perform(get("/get_users/{id}", 1).with(csrf()))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", user));
//                .andExpect(model().attribute("roles", Role.values()));
//                .andExpect(MockMvcResultMatchers.view().name("/user/userEdit"));
    }

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