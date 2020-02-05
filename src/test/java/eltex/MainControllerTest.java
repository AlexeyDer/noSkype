package eltex;


import eltex.controller.WebController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


/**
 * Класс для тестирования главнной страницы
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private WebController controller;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void mainPageTest() throws Exception {
//        this.mockMvc.perform(get("/main"))
//                .andDo(print())
//                .andExpect(authenticated());
////                .andExpect((ResultMatcher) xpath("//*[@id=\"name\"]").string("u"));
//
//    }
}
