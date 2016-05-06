package psiprobe.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import psiprobe.Entities.File;
import psiprobe.Entities.JournalEntry;

import java.util.List;

/**
 * Created by vt on 06.05.16.
 */

@Repository
public class JournalEntryDAOImpl implements JournalEntryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addJournalEntry(JournalEntry entry) {
        sessionFactory.getCurrentSession().save(entry);
    }

    @SuppressWarnings("unchecked")
    public List<JournalEntry> listJournalEntry() {

        return sessionFactory.getCurrentSession().createQuery("from JournalEntry")
                .list();
    }

    public void removeEntry(String uuid) {
        File entry = (File) sessionFactory.getCurrentSession().load(
                JournalEntry.class, uuid);
        if (null != entry) {
            sessionFactory.getCurrentSession().delete(entry);
        }

    }
}
