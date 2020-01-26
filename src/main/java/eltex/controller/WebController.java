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

    @GetMapping({"/index"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping({"/chat"})
    public String chatIndex(Model model) {
        return "chat";
    }

}
