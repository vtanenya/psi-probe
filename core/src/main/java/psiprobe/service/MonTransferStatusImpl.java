package psiprobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psiprobe.DAO.MonTransferStatusDAO;
import psiprobe.Entities.MonTransferStatus;

import java.util.List;

/**
 * Created by vt on 06.05.16.
 */

@Service
public class MonTransferStatusImpl implements MonTransferStatusService{

    @Autowired
    private MonTransferStatusDAO statusDAO;

    @Transactional
    public void addStatus(MonTransferStatus status) {
        statusDAO.addMonTransferStatus(status);
    }

    @Transactional
    public List<MonTransferStatus> listStatuses() {

        return statusDAO.listStatuses();
    }

    @Transactional
    public void removeStatus(String uuid) {
        statusDAO.removeStatus(uuid);
    }
}
