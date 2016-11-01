package com.example.service;

import com.example.repository.model.Metadata;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cavayman on 07.10.2016.
 * <p>
 * Class for geting metadata from file. All files must be saved in uploadDir.
 */
public class MetadataService {

    public Metadata getMetadata(String fileName) {
        File file = new File(fileName);
        Metadata metadata = new Metadata();
        metadata.setFileName(file.getName());
        metadata.setFileSize(convertFromByteToKB(file.length()));
        metadata.setFileCreationDate(new Date(file.lastModified()));
        metadata.setFileLastModifiedDate(new Date(file.lastModified()));
        return metadata;
    }

    private String convertFromByteToKB(Long byteSize) {
        double bytes = byteSize;
        double kilobytes = (bytes / 1024);
        return String.format("%(.2f KB", kilobytes);
    }

    public List<Metadata> findAll() {
        List<Metadata> metadatas = new ArrayList<>();
        File allFiles = new File(StaticVariables.SAVE_DIR);
        if (!allFiles.exists()) {
            allFiles.mkdir();
        }
          for (File file : allFiles.listFiles()) {
                Metadata metadata = getMetadata(file.getName());
                metadatas.add(metadata);
            }


        return metadatas;
    }

    public JSONObject getMetadataJson(Metadata metadata) {
        JSONObject metaJson = new JSONObject();
        metaJson.put("fileName", metadata.getFileName());
        metaJson.put("fileSize", metadata.getFileSize());
        metaJson.put("fileCreationDate", metadata.getFileCreationDate().toString());
        return metaJson;
    }
}
