package eltex;

import eltex.entity.User;
import eltex.repository.UserRepository;
import eltex.service.UserService;
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /**
     * Метод автоматически создает пользователей в программе
     */
    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            // Add user
            userService.registNewUser(new User("u", "p"));
            // Add admin
            userService.registNewAdmin(new User("admin", "p"));
        };
    }

}
