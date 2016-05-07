package psiprobe.Entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vt on 06.05.16.
 */

@Entity
@Table(name = "tbl_integration_mon_transfer_status")
public class MonTransferStatus {

    @Id
    @Column(name = "UUID")
    @GeneratedValue
    private String uuid;

    @Column(name = "object_uuid")
    private String objectUUID;

    @Column(name = "transfer_status")
    private String transferStatus;

    @Column(name = "comment")
    private String comment;

    @Column(name = "change_date")
    private Date changeDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "file_uuid")
    private File file;

    @Column(name = "editdate")
    private Date editDate;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}
