package eltex;

import eltex.been.AppContext;
import eltex.entity.Role;
import eltex.entity.User;
import eltex.repository.UserRepository;
import eltex.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.MBeanExportConfiguration;
import org.springframework.context.annotation.Scope;

import java.util.Collections;
import java.util.Random;

/**
 * Класс для запуска программы
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@SpringBootApplication
public class Application {

    private static final Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppContext.class);

        SpringApplication.run(Application.class, args);
        log.info("Start application!");

    }

}
