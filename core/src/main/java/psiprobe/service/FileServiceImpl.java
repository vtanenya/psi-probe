package psiprobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psiprobe.DAO.FileDAO;
import psiprobe.Entities.File;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDAO fileDAO;

    @Transactional
    public void addFile(File file) {
        fileDAO.addFile(file);
    }

    @Transactional
    public List<File> listFiles() {

        return fileDAO.listFiles();
    }

    @Transactional
    public void removeFile(String uuid) {
        fileDAO.removeFile(uuid);
    }
}
