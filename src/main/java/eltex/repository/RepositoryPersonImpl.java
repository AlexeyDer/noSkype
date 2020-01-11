package eltex.repository;

import eltex.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositoryPersonImpl implements RepositoryPerson {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryPersonImpl.class);

    private SessionFactory sessionFactory;

    @Autowired(required = true)
    @Qualifier(value = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPerson(Person person) {
        Session sessionWrite = this.sessionFactory.openSession();
        sessionWrite.beginTransaction();

        sessionWrite.save(person);
        sessionWrite.getTransaction().commit();
        sessionWrite.close();

        logger.info("person successfully saved. person details: " + person);
    }

    @Override
    public void updatePerson(Person person) {
        /*
        Session sessionUpdate = this.sessionFactory.openSession();
        sessionUpdate.beginTransaction();
        sessionUpdate.createQuery("UPDATE Developer SET fio = 'Lili' WHERE fio = :old_fio")
                .setParameter("old_fio", "Lilil")
                .executeUpdate();
        sessionUpdate.getTransaction().commit();
        sessionUpdate.close();

        logger.info("person successfully update. person details: " + person);
         */
    }

    @Override
    public void removePerson(int id) {
        Session sessionRemove = sessionFactory.openSession();
        sessionRemove.beginTransaction();
        Person person = sessionRemove.get(Person.class, id); // получение по ключевому полю

        if (person != null) {
            sessionRemove.delete(person); // удаление записи
        }

        sessionRemove.getTransaction().commit();
        sessionRemove.close();

        logger.info("person successfully removed. person details: " + person);
    }

    @Override
    public Person getPersonById(int id) {
        /*
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person) session.load(Person.class, new Integer(id));
        logger.info("person successfully loaded. person details: " + person);

        return person;
        */
        return new Person();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> listPersons() {
        Session sessionRead = this.sessionFactory.openSession();
        sessionRead.beginTransaction();
        List<Person> personList = sessionRead.createQuery("FROM Person").list(); // HQL

        sessionRead.getTransaction().commit();
        sessionRead.close();

        for (Person person : personList) {
            logger.info("Person list: " + person);
        }

        return personList;
    }
}
