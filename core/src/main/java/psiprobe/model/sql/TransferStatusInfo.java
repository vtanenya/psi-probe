package psiprobe.model.sql;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Vladimir tanenya
 * 01.05.16.
 */

public class TransferStatusInfo implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4830349275699063972L;

    /** The Constant DS_TEST_SESS_ATTR. */
    public static final String TRANSFER_STATUS_SESS_ATTR = "transferStatusData";

    /** The results. */
    private List<Map<String, String>> results;

    /** The max rows. */
    private int maxRows;

    /** The rows per page. */
    private int rowsPerPage;

    private String objectUUID;

    private String transferStatus;

    private String comment;

    private String dateFrom;

    private String dateTo;

    private String fileName;

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

    public String getObjectUUID() {
        return objectUUID;
    }

    public void setObjectUUID(String objectUUID) {
        this.objectUUID = objectUUID;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}
