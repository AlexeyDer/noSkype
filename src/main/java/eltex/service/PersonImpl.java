package eltex.service;

import eltex.entity.Person;
import eltex.repository.RepositoryPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonImpl implements PersonService {

    private RepositoryPerson repositoryPerson;

    @Autowired(required = true)
    @Qualifier(value = "repositoryPerson")
    public void setRepositoryPerson(RepositoryPerson repositoryPerson) {
        this.repositoryPerson = repositoryPerson;
    }

    @Override
    @Transactional
    public void addPerson(Person person) {
        this.repositoryPerson.addPerson(person);
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        this.repositoryPerson.updatePerson(person);
    }

    @Override
    @Transactional
    public void removePerson(int id) {
        this.repositoryPerson.removePerson(id);
    }

    @Override
    @Transactional
    public Person getPersonById(int id) {
        return this.repositoryPerson.getPersonById(id);
    }

    @Override
    @Transactional
    public List<Person> listPersons() {
        return this.repositoryPerson.listPersons();
    }
}
