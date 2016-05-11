package psiprobe.DAO;

import psiprobe.Entities.File;

import java.util.List;

public interface FileDAO {

    public void addFile(File file);

    public List<File> listFiles();

    public void removeFile(String uuid);
}
