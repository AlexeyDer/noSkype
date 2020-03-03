package eltex.service;

import eltex.controller.RegistrationController;
import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 * Сервис класс для проведение опереций свзязанных с базой данных.
 * Работает с транзакциями
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@Service
@Transactional
public class UserService {
    /**
     * Поле побявления переменной для логгирования
     */
    private static final Logger log = Logger.getLogger(UserService.class.getName());
    /**
     * Поле подключения репозитория для взамимодействия пользвателя с бд
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * Поле для кодирования данных
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Метод регастрации нового пользователя, с ролью админ по выбору
     * @return true если пользователь создан, false если такой пользователь уже существует
     */
    public boolean registNewUser(User user, boolean isAdmin) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        log.info("Search users from db");

        if (userFromDb != null) {
            log.info("User already exists");
            return false;
        }

        if (isAdmin) {
            user.setRoles(Collections.singleton(Role.ADMIN));
            log.info("Create user with role ADMIN");
        } else {
            user.setRoles(Collections.singleton(Role.USER));
            log.info("Create user with role USER");
        }

        String code = passwordEncoder.encode(user.getPassword());
        log.info("Password was encode");
        user.setPassword(code);
        log.info("Password was changed");
        user.setConfirmPassword(code);
        log.info("PasswordConfirm was changed");
        user.setActive(true);
        log.info("Activity Status Set");
        userRepository.save(user);
        log.info("User saved in DB");
        return true;
    }
}
