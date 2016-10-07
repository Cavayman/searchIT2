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


    public File saveTextFile(Part part,String path) throws IOException {
        part.write(path);
        return new File(path);
    }
}
