package psiprobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psiprobe.DAO.PersonDAO;
import psiprobe.Entities.Person;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonDAO personDAO;

    @Transactional
    public void addPerson(Person person) {
        personDAO.addPerson(person);
    }

    @Transactional
    public List<Person> listPersons() {

        return personDAO.listPersons();
    }

    @Transactional
    public void removePerson(String uuid) {
        personDAO.removePerson(uuid);
    }
}
