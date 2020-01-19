package eltex.service;

import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registNewUser(User user) {
        String code = passwordEncoder.encode(user.getPassword());
        user.setPassword(code);
        user.setConfirmPassword(code);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }

    public void registNewAdmin(User user) {
        String code = passwordEncoder.encode(user.getPassword());
        user.setPassword(code);
        user.setConfirmPassword(code);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepository.save(user);
    }
}
