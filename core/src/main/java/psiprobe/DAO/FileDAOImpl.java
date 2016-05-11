package psiprobe.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import psiprobe.Entities.File;

import java.util.List;

@Repository
public class FileDAOImpl implements FileDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addFile(File file) {
        sessionFactory.getCurrentSession().save(file);
    }

    @SuppressWarnings("unchecked")
    public List<File> listFiles() {

        return sessionFactory.getCurrentSession().createQuery("from File")
                .list();
    }

    public void removeFile(String uuid) {
        File file = (File) sessionFactory.getCurrentSession().load(
                File.class, uuid);
        if (null != file) {
            sessionFactory.getCurrentSession().delete(file);
        }

    }
}
