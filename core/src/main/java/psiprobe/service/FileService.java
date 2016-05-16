package psiprobe.service;

import psiprobe.Entities.File;

import java.util.List;

/**
 * @author Vladimir tanenya
 * 01.05.16.
 */

public interface FileService {

    public void addFile(File file);

    public List<File> listFiles();

    public void removeFile(String uuid);
}
