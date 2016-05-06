package psiprobe.Entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vt on 06.05.16.
 */

@Entity
@Table(name = "TBL_LOG")
public class JournalEntry {

    @Id
    @Column(name = "UUID")
    @GeneratedValue
    private String uuid;

//    @Column(name = "senderuuid")
//    private Person sender;

    @Column(name = "event_date")
    private Date date;

    @Column(name = "event_category")
    private String category;

    @Column(name = "event_message_")
    private String message;

    @Column(name = "event_comment_")
    private String comment;

    @Column(name = "event_ip")
    private String ip;

    @Column(name = "subjectuuid")
    private String subject;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

//    public Person getSender() {
//        return sender;
//    }
//
//    public void setSender(Person sender) {
//        this.sender = sender;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
