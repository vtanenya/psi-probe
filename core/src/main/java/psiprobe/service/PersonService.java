package psiprobe.service;

import psiprobe.Entities.Person;

import java.util.List;

/**
 * Created by vt on 06.05.16.
 */
public interface PersonService {

    public void addPerson(Person person);

    public List<Person> listPersons();

    public void removePerson(String uuid);
}
