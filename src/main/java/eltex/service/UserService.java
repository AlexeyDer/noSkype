package eltex.service;

import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
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

        if (userFromDb != null) {
            return false;
        }

        if (isAdmin) {
            user.setRoles(Collections.singleton(Role.ADMIN));
        } else {
            user.setRoles(Collections.singleton(Role.USER));
        }

        String code = passwordEncoder.encode(user.getPassword());
        user.setPassword(code);
        user.setConfirmPassword(code);
        user.setActive(true);

        userRepository.save(user);
        return true;
    }
}
