package eltex.config;

import eltex.service.PersonImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import eltex.entity.Person;
import eltex.service.PersonService;

import java.util.List;

@Configuration
public class SpringConfig {

    @Bean
    public PersonService getPersonService(){
        return new PersonImpl();
    }

}
