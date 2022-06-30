package ru.noSkype;

import ru.noSkype.entity.User;
import ru.noSkype.service.UserService;
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
    @Autowired
    private UserService userService;
    private static final Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Start application!");
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            userService.registNewUser(new User("user", "pass"), false);
            userService.registNewUser(new User("Alexey", "p"), false);
            userService.registNewUser(new User("Olga", "asdaw"), false);
            userService.registNewUser(new User("Evgeniy", "123"), false);
            userService.registNewUser(new User("Max", "p"), false);
            log.info("Create Users");

            userService.registNewUser(new User("admin", "p"), true);
            log.info("Create Admin");
        };
    }

}
