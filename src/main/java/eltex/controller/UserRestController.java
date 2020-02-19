package eltex.controller;

import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Rest controller для получения пользователей в формате JSON
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api")
public class UserRestController {
    /**
     * Поле для работы с пользователями из бд
     */
    @Autowired
    UserRepository userRepository;
    /**
     * Метод для отправки пользователя, по запросу "{/user/{id}}"
     * @return данного пользователя или оповещает, что такого пользователя не найдено
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findByUserName(@PathVariable Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

        return userList;
    }

}
