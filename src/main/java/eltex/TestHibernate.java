package eltex;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import eltex.entity.Person;

import java.util.List;

public class TestHibernate {
    public TestHibernate() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = null;

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println(e.getMessage());
        }

        // добавление данные
        Session sessionWrite = sessionFactory.openSession();
        sessionWrite.beginTransaction();

        Person person1 = new Person(1, "Viktoria", "900", "Viki");

        sessionWrite.save(person1);
        sessionWrite.getTransaction().commit();
        sessionWrite.close();

        // считывание данных
        Session sessionRead = sessionFactory.openSession();
        sessionRead.beginTransaction();
        List<Person> personList = sessionRead.createQuery("FROM Person").list(); // HQL
        personList.forEach(person -> {
            System.out.println(person.getId() + " : " + person.getFio() + " VIKI");
        });
        sessionRead.getTransaction().commit();
        sessionRead.close();

    }
}
