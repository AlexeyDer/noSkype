package eltex.controller;

import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ElementCollection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Контроллер для получения данных пользователей
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@Controller
@RequestMapping("/get_users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    /**
     * Поле побявления переменной для логгирования
     */
    private static final Logger log = Logger.getLogger(UserController.class.getName());
    /**
     * Поле подключения репозитория для взамимодействия пользвателя с бд
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Метод вывода списка пользователей по ссылке  <b>/get_users</b>
     *
     * @return ссылку на список пользователей
     */
    @GetMapping
    public String getUsers(Model model) {
        if (log.isDebugEnabled()) {
            log.debug("getUsers is executed!");
        }
        List<User> users = userRepository.findAll();
        log.info("Users successfully received");
        model.addAttribute("users", users);
        log.info("Users sent successfully to html");
        return "user/userList";
    }
    /**
     * Метод для изменения данных полей пользователя,
     * а также добавление или удаление нужных для него прав.
     *
     * @return возвращает нас на список пользователей
     */
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("getUsers is executed!");
        }
        User user = userRepository.findById(id);
        log.info("User by id was received");
        if (user == null) {
            log.info("User redefined to 404 error page");
            return "/errors/404";
        }
        user.setUsername(username);
        log.info("Username changed");
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        log.info("User role changes");
        userRepository.save(user);
        log.info("User saved");
        return "redirect:/get_users";
    }
    /**
     * Метод позволяющий после ссылки <b>/get_users/</b> по номеру его id
     * получение данных об этом пользователе.
     * Если пользователя с данным id не существует, то выдаст ошибку
     *
     * @param
     * @return возвращает на ссылку иззменения пользователя
     */
    @GetMapping("/{id}")
    public String userData(@PathVariable("id") Long id, Model model) {
        if (log.isDebugEnabled()) {
            log.debug("userData is executed!");
        }
        User user = userRepository.findById(id);
        log.info("User by id was received");
        if (user == null) {
            log.info("User redefined to 404 error page");
            return "/errors/404";
        }
        model.addAttribute("user", user);
        log.info("User sent successfully to html");
        model.addAttribute("roles", Role.values());
        log.info("Roles sent successfully to html");
        return "user/userEdit";
    }

}
