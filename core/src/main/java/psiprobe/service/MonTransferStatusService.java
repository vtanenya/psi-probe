package psiprobe.service;

import psiprobe.Entities.MonTransferStatus;

import java.util.List;

public interface MonTransferStatusService {

    public void addStatus(MonTransferStatus status);

    public List<MonTransferStatus> listStatuses(int maxRows, String objectUUID, String dateFrom, String dateTo, String status, String comment, String fileName);

    public void removeStatus(String uuid);
}
