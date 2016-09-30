package com.example.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cavayman on 29.09.2016.
 */
public class FileService {

    public static File file;

    public String getTextFile(File file){
        String resoult="";
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
            resoult+=sCurrentLine;
            }
        return resoult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resoult;
    }
}
