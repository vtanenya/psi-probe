package psiprobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psiprobe.DAO.MonTransferStatusDAO;
import psiprobe.Entities.MonTransferStatus;

import java.util.List;

/**
 * @author Vladimir tanenya
 * 01.05.16.
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
    public List<MonTransferStatus> listStatuses(int maxRows, String objectUUID, String dateFrom, String dateTo, String status, String comment, String fileName) {

        return statusDAO.listStatuses(maxRows, objectUUID, dateFrom, dateTo, status, comment, fileName);
    }

    @Transactional
    public void removeStatus(String uuid) {
        statusDAO.removeStatus(uuid);
    }
}
