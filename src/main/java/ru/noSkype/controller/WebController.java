package ru.noSkype.controller;

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
     * Поле объявления переменной для логирования
     */
    private static final Logger log = Logger.getLogger(WebController.class.getName());

    /**
     * Метод @return страницу приветствия
     */
    @GetMapping({"/", "/greeting"})
    public String greeting(Model model) {
        if (log.isDebugEnabled()) {
            log.debug("WebController is executed!");
        }
        return "greeting";
    }
}
