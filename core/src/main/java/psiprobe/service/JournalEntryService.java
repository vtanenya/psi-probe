package psiprobe.service;

import psiprobe.Entities.JournalEntry;

import java.util.List;

/**
 * Created by vt on 06.05.16.
 */
public interface JournalEntryService {

    public void addEntry(JournalEntry entry);

    public List<JournalEntry> listEntrys();

    public void removeEntry(String uuid);
}
