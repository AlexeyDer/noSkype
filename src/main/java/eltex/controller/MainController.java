package eltex.controller;

import eltex.entity.User;
import eltex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getMainPage(Model model) {
        return "main";
    }

    @PostMapping
    public String searchUsers(Model model, @RequestParam String searchUsername) {
        User user = userRepository.findByUsername(searchUsername);
        if (user == null) {
            return "main";
        }

        return "redirect:/main#" + user.getUsername();
    }
}
