package ru.noSkype.controller;

import ru.noSkype.entity.User;
import ru.noSkype.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер главной страницы программы
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@Controller
@RequestMapping("/main")
public class MainController {
    /**
     * Поле объявления переменной для логирования
     */
    private static final Logger log = Logger.getLogger(MainController.class.getName());
    /**
     * Поле подключения репозитория для взаимодействия пользователя с бд
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Метод @return главную страницу программы
     */
    @GetMapping
    public String getMainPage(Model model) {
        if (log.isDebugEnabled()) {
            log.debug("MainController is executed!");
        }
        model.addAttribute("companion", "Choose...");
        return "main";
    }

    /**
     * Метод поиска пользователя для дальнейшего общения с ним
     *
     * @return возвращает главную страницу программы с идентификатором пользователя,
     * с которым мы хотим связаться
     */
    @PostMapping
    public String searchUsers(Model model, @RequestParam String searchUsername) {
        log.info(searchUsername);

        User user = userRepository.findByUsername(searchUsername);
        if (user == null) {
            log.info("Search user not found: " + searchUsername);
            model.addAttribute("error", "Not found");
            return "main";
        }
        log.info("Search user was found: " + user.getUsername());
        return "redirect:/main#" + user.getUsername();
    }
}
