package com.example.service;

import com.example.repository.dao.impl.MetadataDAOImpl;
import com.example.repository.model.Metadata;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by cavayman on 07.10.2016.
 */
public class MetadataService {
    private MetadataDAOImpl metadataDAO=new MetadataDAOImpl(com.example.repository.model.Metadata.class);

    public Metadata getMetadataByName(String name){
        Metadata metadata=metadataDAO.findByName(name);
        System.out.println("FROM SERVICE"+name);
        System.out.println("FROM SERVICE"+metadata);


        return metadata;
    }
    public void saveMetadata(File savedFile) {
        System.out.println(savedFile.getPath());
        System.out.println(savedFile.getName());
        System.out.println(savedFile.getAbsoluteFile());
        com.example.repository.model.Metadata metadata = new com.example.repository.model.Metadata();
        metadata.setFileName(savedFile.getName());
        metadata.setFileSize(convertFromByteToKB(savedFile.length()));
        metadata.setFileCreationDate(new Date(savedFile.lastModified()));
        metadata.setFileLastModifiedDate(new Date(savedFile.lastModified()));
        metadataDAO.save(metadata);
    }

    private String convertFromByteToKB(Long byteSize) {
        double bytes = byteSize;
        double kilobytes = (bytes / 1024);
        System.out.println(kilobytes);
        return String.format("%(.2f KB", kilobytes);
    }
    public List<Metadata> findAll(){
      return  metadataDAO.findAll();
    }


    public JSONObject getMetadataJson( Metadata metadata) {
        JSONObject metaJson=new JSONObject();
        metaJson.put("id",metadata.getMetadata_id());
        metaJson.put("fileName",metadata.getFileName());
        metaJson.put("fileSize",metadata.getFileSize());
        metaJson.put("fileCreationDate",metadata.getFileCreationDate().toString());
        metaJson.put("fileLastModifiedDate",metadata.getFileLastModifiedDate().toString());
        return metaJson;
    }
    public Metadata findByID(Integer id){
        return metadataDAO.findById(id);
    }
}
