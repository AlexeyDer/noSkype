package eltex;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import eltex.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class TestHibernate {
    public static void testHibernate() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();
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

    }
}
