package eltex;

import eltex.entity.User;
import eltex.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
     * Поле объявления переменной для логирования
     */
    private static final Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Start application!");
    }

    /**
     * Метод автоматически создает пользователей в программе
     */
    @Bean
    public CommandLineRunner demo() {
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
