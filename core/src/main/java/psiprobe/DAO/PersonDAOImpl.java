package psiprobe.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import psiprobe.Entities.File;
import psiprobe.Entities.Person;

import java.util.List;

/**
 * @author Vladimir tanenya
 * 01.05.16.
 */

@Repository
public class PersonDAOImpl implements PersonDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void addPerson(Person person) {
        sessionFactory.getCurrentSession().save(person);
    }

    @SuppressWarnings("unchecked")
    public List<Person> listPersons() {

        return sessionFactory.getCurrentSession().createQuery("from Person")
                .list();
    }

    public void removePerson(String uuid) {
        File person = (File) sessionFactory.getCurrentSession().load(
                Person.class, uuid);
        if (null != person) {
            sessionFactory.getCurrentSession().delete(person);
        }

    }
}
