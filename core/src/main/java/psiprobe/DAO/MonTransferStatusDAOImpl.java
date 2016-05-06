package psiprobe.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import psiprobe.Entities.File;
import psiprobe.Entities.MonTransferStatus;

import java.util.List;

/**
 * Created by vt on 06.05.16.
 */

@Repository
public class MonTransferStatusDAOImpl implements MonTransferStatusDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void addMonTransferStatus(MonTransferStatus status) {
        sessionFactory.getCurrentSession().save(status);
    }

    @SuppressWarnings("unchecked")
    public List<MonTransferStatus> listStatuses() {

        return sessionFactory.getCurrentSession().createQuery("from MonTransferStatus")
                .list();
    }

    public void removeStatus(String uuid) {
        File status = (File) sessionFactory.getCurrentSession().load(
                MonTransferStatus.class, uuid);
        if (null != status) {
            sessionFactory.getCurrentSession().delete(status);
        }

    }
}
