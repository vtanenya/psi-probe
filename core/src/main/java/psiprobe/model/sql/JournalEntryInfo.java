package psiprobe.model.sql;

import psiprobe.Entities.JournalEntry;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by vtanenya on 10.05.16.
 */
public class JournalEntryInfo implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5399598239369004512L;

    /** The Constant DS_TEST_SESS_ATTR. */
    public static final String JOURNAL_ENTRY_SESS_ATTR = "journalEntryData";

    /** The results. */
    private List<Map<String, String>> results;

    /** The max rows. */
    private int maxRows;

    /** The rows per page. */
    private int rowsPerPage;

    private String senderLastname;

    private String dateFrom;

    private String dateTo;

    private String eventMessage;

    private String subjectUUID;

    /**
     * Gets the results.
     *
     * @return the results
     */
    public List<Map<String, String>> getResults() {
        return results;
    }

    /**
     * Sets the results.
     *
     * @param results the results
     */
    public void setResults(List<Map<String, String>> results) {
        this.results = results;
    }

    /**
     * Gets the max rows.
     *
     * @return the max rows
     */
    public int getMaxRows() {
        return maxRows;
    }

    /**
     * Sets the max rows.
     *
     * @param maxRows the new max rows
     */
    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    /**
     * Gets the rows per page.
     *
     * @return the rows per page
     */
    public int getRowsPerPage() {
        return rowsPerPage;
    }

    /**
     * Sets the rows per page.
     *
     * @param rowsPerPage the new rows per page
     */
    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public String getSenderLastname() {
        return senderLastname;
    }

    public void setSenderLastname(String senderLastname) {
        this.senderLastname = senderLastname;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public String getSubjectUUID() {
        return subjectUUID;
    }

    public void setSubjectUUID(String subjectUUID) {
        this.subjectUUID = subjectUUID;
    }
}
