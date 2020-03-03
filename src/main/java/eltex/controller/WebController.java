package eltex.controller;

import eltex.Application;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для страницы приветствия
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */

@Controller
public class WebController {
    /**
     * Поле побявления переменной для логгирования
     */
    private static final Logger log = Logger.getLogger(WebController.class.getName());
    /**
     * Метод @return страницу приветствия
     */
    @GetMapping({"/", "/greeting"})
    public String greeting(Model model) {
        //logs debug message
        if(log.isDebugEnabled()){
            log.debug("WebController is executed!");
        }
        return "greeting";
    }
}
