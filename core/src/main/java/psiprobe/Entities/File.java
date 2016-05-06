package psiprobe.Entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vt on 06.05.16.
 */

@Entity
@Table(name = "tbl_dbfile")
public class File {

    @Id
    @Column(name = "UUID")
    @GeneratedValue
    private String uuid;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "creationdate")
    private Date creationDate;

//    @Column(name = "f_author")
//    private Person author;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

//    public Person getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Person author) {
//        this.author = author;
//    }
}
