package eltex;

import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import eltex.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

/**
 * Класс для запуска программы
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@SpringBootApplication
public class Application {
    /**
     * Поле подключения сервиса пользователя, с реализацией разной логики
     */
    @Autowired
    private UserService userService;
    /**
     * Поле побявления переменной для логгирования
     */
    private static final Logger log = Logger.getLogger(Application.class.getName());
    /**
     * Поля для взаимодействия программы с бд
     */
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Start application!");
    }
    /**
     * Метод автоматически создает пользователей в программе
     */
    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            // Add user
            userService.registNewUser(new User("u", "p"), false);
            log.info("Create User");
            // Add admin
            userService.registNewUser(new User("admin", "p"), true);
            log.info("Create Admin");
        };
    }

}
