package eltex;

import eltex.controller.WebController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс для тестирования
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private WebController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoad() throws Exception {

        /**
         *  Через подменненый веб-слой делаем запрос на главную страницу проекта,
         *  Выводим результат, который вернул нам сервер
         *  Проверяем что код возврата был 200
         *  И проверяем, что в контенте у нас содержиться наша строка "..."
         */

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, you are on the main page noSkype programm!")));
    }


}
