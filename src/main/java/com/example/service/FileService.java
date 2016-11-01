package com.example.service;


import javax.servlet.http.Part;
import java.io.*;


/**
 * Created by cavayman on 29.09.2016.
 */
public class FileService {


    public void saveTextFile(Part part,String path) throws IOException {
        part.write(path);
    }
}
