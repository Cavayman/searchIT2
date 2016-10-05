package com.example.service;

//import com.example.util.PropertyGetterSingleton;
//import org.json.Property;
//import org.json.simple.JSONObject;

import com.example.repository.dao.MetadataDAO;
import com.example.repository.dao.impl.MetadataDAOImpl;
import com.example.repository.model.Metadata;

import javax.servlet.http.Part;
import java.io.*;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by cavayman on 29.09.2016.
 */
public class FileService {

    private MetadataDAOImpl metadataDAO=new MetadataDAOImpl(Metadata.class);
//    delete this. duplicated method with  JSON Maked readFile
    public String getTextFile(String fileName) {
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                content.append(sCurrentLine);
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void saveTextFile(Part part) throws IOException {
        part.write(part.getSubmittedFileName());
        saveMetadata( new File(part.getSubmittedFileName()));
    }

    private void saveMetadata(File savedFile) {
        Metadata metadata = new Metadata();
        metadata.setFileName(savedFile.getName());
        metadata.setFileSize(convertFromByteToKB(savedFile.length()));
        metadata.setFileCreationDate(new Date(savedFile.lastModified()));
        metadata.setFileLastModifiedDate(new Date(savedFile.lastModified()));
        metadataDAO.save(metadata);
    }

    private String convertFromByteToKB(Long byteSize) {
        double kilobytes = (byteSize / 1024);
        return String.format("%(.1f KB", kilobytes);
    }
    
}
