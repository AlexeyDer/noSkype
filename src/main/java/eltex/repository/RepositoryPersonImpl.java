package eltex.repository;

import eltex.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryPersonImpl implements RepositoryPerson {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryPersonImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = null;

        try {
            this.sessionFactory =
                    new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void addPerson(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(person);
        logger.info("person successfully saved. person details: " + person);
    }

    @Override
    public void updatePerson(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(person);
        logger.info("person successfully update. person details: " + person);
    }

    @Override
    public void removePerson(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person) session.load(Person.class, new Integer(id));

        if (person != null) {
            session.delete(person);
        }
        logger.info("person successfully removed. person details: " + person);
    }

    @Override
    public Person getPersonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person) session.load(Person.class, new Integer(id));
        logger.info("person successfully loaded. person details: " + person);

        return person;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> listPersons() {

        if (sessionFactory == null) {
            System.out.println("ФАБРИКА СЕССИЙ = NULL");
        } else {
            Session sessionRead = sessionFactory.openSession();
            sessionRead.beginTransaction();
            List<Person> personList = sessionRead.createQuery("FROM VikiUser").list(); // HQL
            sessionRead.getTransaction().commit();
            sessionRead.close();

            for (Person person : personList) {
                logger.info("person list: " + person);
            }

            return personList;

        }
        return null;
    }
}
