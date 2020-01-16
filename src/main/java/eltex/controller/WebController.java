package eltex.controller;

import eltex.entity.User;
import eltex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Controller
class WebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get_users/{id}")
    public String userData(@PathVariable("id") Long id, Model model) {
        if (userRepository.findById(id) == null)
            return "/errors/404";
        List<User> us = new ArrayList<>(1);
        us.add(userRepository.findById(id));
        model.addAttribute("users", us);
        return "users";
    }

    @GetMapping("/get_users")
    public String getUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping({"/", "/greeting"})
    public String greeting(Model model) {
        return "greeting";
    }

}
