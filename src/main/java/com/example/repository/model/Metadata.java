package com.example.repository.model;

import java.util.Date;

/**
 * Created by cavayman on 30.09.2016.
 */
public class Metadata {


    private Integer metadata_id;
    private String fileName;
    private String fileSize;
    private Date fileCreationDate;
    private Date fileLastModifiedDate;

    public Metadata() {
    }

    public Integer getMetadata_id() {
        return metadata_id;
    }

    public void setMetadata_id(Integer metadata_id) {
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


    public void setFileLastModifiedDate(Date fileLastModifiedDate) {
        this.fileLastModifiedDate = fileLastModifiedDate;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "metadata_id=" + metadata_id +
                ", fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileCreationDate=" + fileCreationDate +
                ", fileLastModifiedDate=" + fileLastModifiedDate +
                '}';
    }
}
