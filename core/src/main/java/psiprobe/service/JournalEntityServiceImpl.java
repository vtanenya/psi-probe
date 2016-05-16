package psiprobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import psiprobe.DAO.JournalEntryDAO;
import psiprobe.Entities.JournalEntry;

import java.util.List;

/**
 * @author Vladimir tanenya
 * 01.05.16.
 */

@Service
public class JournalEntityServiceImpl implements JournalEntryService{

    @Autowired
    private JournalEntryDAO entryDAO;

    @Transactional
    public void addEntry(JournalEntry entry) {
        entryDAO.addJournalEntry(entry);
    }

    @Transactional
    public List<JournalEntry> listEntrys(int maxRows, String senderLastname, String dateFrom, String dateTo, String eventMessage, String subjectUUID) {

        return entryDAO.listJournalEntry(maxRows, senderLastname, dateFrom, dateTo, eventMessage, subjectUUID);
    }

    @Transactional
    public void removeEntry(String uuid) {
        entryDAO.removeEntry(uuid);
    }
}
