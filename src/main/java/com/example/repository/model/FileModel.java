package com.example.repository.model;

import javax.persistence.*;

/**
 * Created by cavayman on 30.09.2016.
 */
@Entity
@Table(name="File")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer file_Id;
    private String filePath;
    @OneToOne(cascade = CascadeType.ALL)
    private Metadata metadata;

    public FileModel() {
    }

    public int getFile_Id() {
        return file_Id;
    }

    public void setFile_Id(int file_Id) {
        this.file_Id = file_Id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
