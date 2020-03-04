package eltex.controller;

import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Rest controller для получения или отправвления пользователей в формате JSON
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api")
public class UserRestController {
    /**
     * Поле побявления переменной для логгирования
     */
    private static final Logger log = Logger.getLogger(MainController.class.getName());
    /**
     * Поле для работы с пользователями из бд
     */
    @Autowired
    UserRepository userRepository;
    /**
     * Метод для отправки пользователя, по запросу "{/user/{id}}"
     * @return данного пользователя или оповещает, что такого пользователя не существует
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findByUserName(@PathVariable Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            log.info("User not found by id: " + id.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Get user request with id: " + id.toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    /**
     * Метод для отправлки списка пользователя по запросу {/user/list}
     * @return возвращает список пользователей
     */
    @GetMapping("user/list")
    public List<User> getListUsers() {
        List<User> userList = userRepository.findAll();

        for(int i = 0; i < userList.size(); i++) {
            for (Role r: userList.get(i).getRoles()) {
                if (r == Role.ADMIN) {
                    userList.remove(i);
                }
            }
        }
        log.info("List users Received successfully");
        return userList;
    }

}
