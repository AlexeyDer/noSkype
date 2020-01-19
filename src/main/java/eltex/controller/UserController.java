package eltex.controller;

import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ElementCollection;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/get_users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/userList";
    }


    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam ("userId") Long id) {
        User user = userRepository.findById(id);
        if (user == null)
            return "/errors/404";
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return "redirect:/get_users";
    }


    @GetMapping("/{id}")
    public String userData(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id);
        if (user == null)
            return "/errors/404";
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/userEdit";
    }

}
