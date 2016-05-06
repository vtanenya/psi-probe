package psiprobe.DAO;

import psiprobe.Entities.File;

import java.util.List;

/**
 * Created by vt on 06.05.16.
 */
public interface FileDAO {

    public void addFile(File file);

    public List<File> listFiles();

    public void removeFile(String uuid);
}
