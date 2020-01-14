package eltex.config;

import eltex.repository.RepositoryPersonImpl;
import eltex.service.PersonImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public PersonImpl personService() {
        return new PersonImpl();
    }

    @Bean
    public SessionFactory sessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println(e.getMessage());
        }
        return factory;
    }

    @Bean
    public RepositoryPersonImpl repositoryPerson() {
        return new RepositoryPersonImpl();
    }
}
