package com.example.repository.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cavayman on 30.09.2016.
 */
@Entity
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer metadata_id;
    private String fileName;
    private String fileSize;
    private Date fileCreationDate;
    @OneToOne(cascade = CascadeType.ALL)
    private FileModel file;

    public Metadata() {
    }

    public int getMetadata_id() {
        return metadata_id;
    }

    public void setMetadata_id(int metadata_id) {
        this.metadata_id = metadata_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Date getFileCreationDate() {
        return fileCreationDate;
    }

    public void setFileCreationDate(Date fileCreationDate) {
        this.fileCreationDate = fileCreationDate;
    }

    public FileModel getFile() {
        return file;
    }

    public void setFile(FileModel file) {
        this.file = file;
    }
}
