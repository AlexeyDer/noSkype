package eltex.controller;

import eltex.entity.User;
import eltex.repository.UserRepository;
import eltex.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * Котроллер для регестрации
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@Controller
public class RegistrationController {
    /**
     * Поле объявления переменной для логирования
     */
    private static final Logger log = Logger.getLogger(RegistrationController.class.getName());
    /**
     * Поле адреса страницы регистрации
     */
    private static final String URL_REGISTRATION_ADDRESS = "registration";
    /**
     * Поле подключения репозитория для взаимодействия пользователя с бд
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * Поле подключения сервиса пользователя, с реализацией разной логики
     */
    @Autowired
    private UserService userService;
    /**
     * Метод @return страницу регестрации
     */
    @GetMapping("/registration")
    public String registration() {
        if(log.isDebugEnabled()){
            log.debug("RegistrationContoller is executed!");
        }
        return URL_REGISTRATION_ADDRESS;
    }
    /**
     * Метод для добавления пользователей в базу данных
     *
     * @return страницу авторизации
     */
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        log.info("Find user from db");
        // Проверка на сходство введенных паролей
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.put("message", "Passwords isn't equals!");
            log.info("Password isn't equals!");
            return URL_REGISTRATION_ADDRESS;
        }

        if (userFromDb != null) {
            model.put("message", "Person exists!");
            log.info("User not found in DB!");
            return URL_REGISTRATION_ADDRESS;
        }

        userService.registNewUser(user, false);
        log.info("User created.");

        return "redirect:/login";
    }
}
