package eltex;

import eltex.controller.WebController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Класс для тестирования страницы авторизации
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
    /**
     * Поле тестируемого контроллера
     */
    @Autowired
    private WebController controller;
    /**
     * Поле для работы с mockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Через подменненый веб-слой делаем запрос на главную страницу проекта,
     * Выводим результат, который вернул нам сервер
     * Проверяем что код возврата был 200
     * И проверяем, что в контенте у нас содержиться наша строка "..."
     */
    @Test
    public void contextLoad() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, you are on the main page noSkype programm!")));
    }

    /**
     * Указываем адрес странички, который требует авторизации
     * Выводим результат
     * Проверяем, что система ожидает статус отличный от 200-ого
     * Проверка, что система подкидывает нам нужный адресс /login
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
     * Проверяем пользователя с коректными данными
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
     * Проверяем пользователя с некоректными данными, возвращая нас на страничку ошибки входа
     */
    @Test
    public void inCorrectLogin() throws Exception {
        this.mockMvc.perform(formLogin().user("u").password("wdwdwd"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }


}
