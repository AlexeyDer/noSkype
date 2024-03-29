package ru.noSkype;

import ru.noSkype.entity.User;
import ru.noSkype.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Интеграционное тестирование страницы авторизации
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
    /**
     * Поле подключения сервиса пользователя, с реализацией разной логики
     */
    @Autowired
    private UserService userService;
    /**
     * Поле для работы с mockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Метод настройки класса тестирования, который добавляет пользователя
     */
    @Before
    public void setUp() {
        userService.registNewUser(new User("u", "p"), false);
    }

    /**
     * Через подмененный веб-слой делаем запрос на главную страницу проекта,
     * Выводим результат, который вернул нам сервер
     * Проверяем что код возврата был 200
     * И проверяем, что в контенте у нас содержится наша строка "..."
     */
    @Test
    public void contextLoad() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Указываем адрес странички, который требует авторизации
     * Выводим результат
     * Проверяем, что система ожидает статус отличный от 200-ого
     * Проверка, что система подкидывает нам нужный адрес /login
     */
    @Test
    public void loginTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    /**
     * Данный тест смотрит, как мы в контексте определили login page и вызывает обращение к этой страничке.
     * Проверяем пользователя с корректными данными
     */
    @Test
    public void correctLogin() throws Exception {
        this.mockMvc.perform(formLogin().user("u").password("p"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Данный тест смотрит, как мы в контексте определили login page и вызывает обращение к этой страничке.
     * Проверяем пользователя с некорректными данными, возвращая нас на страничку ошибки входа
     */
    @Test
    public void incorrectLogin() throws Exception {
        this.mockMvc.perform(formLogin().user("u").password("wdwdwd"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
}
