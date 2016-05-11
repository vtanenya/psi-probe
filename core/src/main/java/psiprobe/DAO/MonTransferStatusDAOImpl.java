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
import psiprobe.Entities.MonTransferStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class MonTransferStatusDAOImpl implements MonTransferStatusDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void addMonTransferStatus(MonTransferStatus status) {
        sessionFactory.getCurrentSession().save(status);
    }

    @SuppressWarnings("unchecked")
    public List<MonTransferStatus> listStatuses(int maxRows, String objectUUID, String dateFrom, String dateTo, String status, String comment, String fileName) {

        Session s = sessionFactory.getCurrentSession();

        Criteria c = s.createCriteria(MonTransferStatus.class, "status");

        if (StringUtils.isNotEmpty(fileName)) {
            c.createAlias("status.file", "file");
            c.add(Restrictions.ilike("file.fileName", fileName, MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotEmpty(dateFrom) && StringUtils.isNotEmpty(dateTo)) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date from = format.parse(dateFrom);
                Date to = format.parse(dateTo);
                c.add(Restrictions.between("editDate", from, to));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotEmpty(objectUUID))
            c.add(Restrictions.eq("objectUUID", objectUUID));

        if (StringUtils.isNotEmpty(status))
            c.add(Restrictions.ilike("transferStatus", status, MatchMode.ANYWHERE));

        if (StringUtils.isNotEmpty(comment))
            c.add(Restrictions.ilike("comment", comment, MatchMode.ANYWHERE));

        c.setMaxResults(maxRows);
        c.addOrder(Order.asc("editDate"));

        return c.list();
    }

    public void removeStatus(String uuid) {
        File status = (File) sessionFactory.getCurrentSession().load(
                MonTransferStatus.class, uuid);
        if (null != status) {
            sessionFactory.getCurrentSession().delete(status);
        }

    }
}
