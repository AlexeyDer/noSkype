package ru.noSkype.controller;

import ru.noSkype.entity.User;
import ru.noSkype.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс для тестирования главной страницы
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("usr")
public class MainControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private WebController controller;

    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        userService.registNewUser(new User("u", "p"), false);
    }

    @Test
    public void getMainPage() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("main"));
    }

    /**
     * Метод тестирующий поиск пользователей и переход на реферальную ссылку
     */
    @Test
    public void searchUsers() throws Exception {
        this.mockMvc.perform(post("/main").with(csrf())
                .param("searchUsername", "u"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main#u"));
    }

    /**
     * Метод тестирующий поиск пользователей, указывая что пользователя с данным именем не существует
     */
    @Test
    public void searchUsersError() throws Exception {
        this.mockMvc.perform(post("/main").with(csrf())
                .param("searchUsername", "bad_name_of_user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("error", "Not found"))
                .andExpect(MockMvcResultMatchers.view().name("main"));
    }

    /**
     * Метод проверяет отображение имени пользователя в панели навигации
     * после входа в систему
     */
    @Test
    public void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(MockMvcResultMatchers.xpath("//*[@id='name']").string("usr"));
    }

    /**
     * Метод для тестирования контроллера страницы приветствия
     */
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    /**
     * Метод для тестирования контроллера главной страницы
     */
    @Test
    public void mainContexLoads() throws Exception {
        assertThat(mainController).isNotNull();
    }

}
