package psiprobe.DAO;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import psiprobe.Entities.File;
import psiprobe.Entities.JournalEntry;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Vladimir tanenya
 * 01.05.16.
 */

@Repository
public class JournalEntryDAOImpl implements JournalEntryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addJournalEntry(JournalEntry entry) {
        sessionFactory.getCurrentSession().save(entry);
    }

    @SuppressWarnings("unchecked")
    public List<JournalEntry> listJournalEntry(int maxRows, String senderLastname, String dateFrom, String dateTo, String eventMessage, String subjectUUID) {

        Session s = sessionFactory.getCurrentSession();

        Criteria c = s.createCriteria(JournalEntry.class, "entry");

        if (StringUtils.isNotEmpty(senderLastname))
        {
            c.createAlias("entry.sender", "person");
            c.add(Restrictions.ilike("person.lastName", senderLastname, MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotEmpty(dateFrom) && StringUtils.isNotEmpty(dateTo)) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date from = format.parse(dateFrom);
                Date to = format.parse(dateTo);
                c.add(Restrictions.between("date", from, to));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotEmpty(eventMessage))
            c.add(Restrictions.ilike("message", eventMessage, MatchMode.ANYWHERE));

        if (StringUtils.isNotEmpty(subjectUUID))
            c.add(Restrictions.eq("subject", subjectUUID));

        c.setMaxResults(maxRows);
        c.addOrder(Order.desc("date"));

        return c.list();
    }

    public void removeEntry(String uuid) {
        File entry = (File) sessionFactory.getCurrentSession().load(
                JournalEntry.class, uuid);
        if (null != entry) {
            sessionFactory.getCurrentSession().delete(entry);
        }
    }
}
