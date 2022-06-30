package ru.noSkype.service;

import ru.noSkype.entity.Role;
import ru.noSkype.entity.User;
import ru.noSkype.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

/**
 * Сервис класс для проведение операций связанных с базой данных.
 * Работает с транзакциями
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@Service
@Transactional
public class UserService {
    /**
     * Поле объявления переменной для логирования
     */
    private static final Logger log = Logger.getLogger(UserService.class.getName());
    /**
     * Поле подключения репозитория для взаимодействия пользователя с бд
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
     *
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
