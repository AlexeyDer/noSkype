package eltex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
class WebController {
    @GetMapping({"/", "/greeting"})
    public String greeting(Model model) {
        return "greeting";
    }

}
