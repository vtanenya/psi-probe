package psiprobe.service;

import psiprobe.Entities.File;

import java.util.List;

public interface FileService {

    public void addFile(File file);

    public List<File> listFiles();

    public void removeFile(String uuid);
}
