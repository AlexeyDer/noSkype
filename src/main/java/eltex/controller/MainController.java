package eltex.controller;

import eltex.entity.User;
import eltex.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        User user = userRepository.findByUsername(searchUsername);
        if (user == null) {
            log.info("Search user not found");
            model.addAttribute("error", "Not found");
            return "main";
        }
        log.info("Search user was found");
        return "redirect:/main#" + user.getUsername();
    }

}
