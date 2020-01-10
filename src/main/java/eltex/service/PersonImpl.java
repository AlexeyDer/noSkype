package eltex.service;

import lombok.Setter;
import eltex.repository.RepositoryPerson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import eltex.entity.Person;

import java.util.List;

@Service
public class PersonImpl implements PersonService {
    @Setter
    private RepositoryPerson repositoryPerson;

    @Override
    @Transactional
    public void addPerson(Person person) {
        if (this.repositoryPerson == null) {

        }
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
