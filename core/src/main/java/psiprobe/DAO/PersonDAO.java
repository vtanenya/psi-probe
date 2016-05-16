package psiprobe.DAO;

import psiprobe.Entities.Person;

import java.util.List;

/**
 * @author Vladimir tanenya
 * 01.05.16.
 */

public interface PersonDAO {

    public void addPerson(Person person);

    public List<Person> listPersons();

    public void removePerson(String uuid);
}
