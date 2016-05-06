package psiprobe.DAO;

import psiprobe.Entities.MonTransferStatus;

import java.util.List;

/**
 * Created by vt on 06.05.16.
 */
public interface MonTransferStatusDAO {

    public void addMonTransferStatus(MonTransferStatus status);

    public List<MonTransferStatus> listStatuses();

    public void removeStatus(String uuid);
}
