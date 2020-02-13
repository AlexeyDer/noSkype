package eltex.controller;

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
    @GetMapping({"/", "/greeting"})
    public String greeting(Model model) {
        return "greeting";
    }
}
