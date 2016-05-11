package psiprobe.service;

import psiprobe.Entities.JournalEntry;

import java.util.List;

public interface JournalEntryService {

    public void addEntry(JournalEntry entry);

    public List<JournalEntry> listEntrys(int maxRows, String senderLastname, String dateFrom, String dateTo, String eventMessage, String subjectUUID);

    public void removeEntry(String uuid);
}
