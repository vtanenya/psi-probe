package psiprobe.DAO;

import psiprobe.Entities.JournalEntry;

import java.util.List;

/**
 * Created by vt on 06.05.16.
 */
public interface JournalEntryDAO {

    public void addJournalEntry(JournalEntry entry);

    public List<JournalEntry> listJournalEntry(int maxRows, String senderLastname, String dateFrom, String dateTo, String eventMessage, String subjectUUID);

    public void removeEntry(String uuid);
}
