package eltex.service;

import eltex.entity.Person;

import java.util.List;

public interface PersonService {
    public void addPerson(Person person);

    public void updatePerson(Person person);

    public void removePerson(int id);

    public Person getPersonById(int id);

    public List<Person> listPersons();
}
