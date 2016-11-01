package com.example.util.FilterMakerImpl;


import com.example.repository.model.Metadata;
import com.example.service.MetadataService;
import com.example.util.FilterConfig;
import com.example.util.FilterMakerInterface;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cavayman on 06.10.2016.
 *
 * This class is implementing FilterMakerInterface and setting all behaviour for reading text,filtering.
 */
public class SimpleTextFilterImpl implements FilterMakerInterface {

    private FilterConfig filterConfig;
    private MetadataService metadataService;

    public SimpleTextFilterImpl() {
        metadataService=new MetadataService();
    }

    @Override
    public  List<String> filterText() {
        List<String> contents = new ArrayList<String>();
        List<String> wordsList = readFile();
        for (String word : wordsList) {
            String acceptable = filterWord(word);
            if (acceptable != null) {
                contents.add(acceptable);
            }
        }
        if(contents.isEmpty()){
            contents.add("Sorry!I cant find anything to show you.");
        }
        return contents;
    }

    @Override
    public  void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig=filterConfig;
    }

    @Override
    public JSONObject getMetadataJson() {
        JSONObject metaJson=new JSONObject();
        Metadata metadata=metadataService.getMetadata(filterConfig.getFileName());
        metaJson.put("fileName",metadata.getFileName());
        metaJson.put("fileSize",metadata.getFileSize());
        metaJson.put("fileCreationDate",metadata.getFileCreationDate().toString());
        return metaJson;
    }


    private synchronized List<String> readFile() {
        List<String> wordsList=new ArrayList<>();
        try (FileReader fileReader = new FileReader(filterConfig.getFileName());
             BufferedReader reader = new BufferedReader(fileReader)) {
            if (filterConfig.getLimit() != 10000) {
                char[] buffer = new char[filterConfig.getLimit()];
                reader.read(buffer, 0, filterConfig.getLimit());
                String wholeString = new String(String.valueOf(buffer).getBytes(StandardCharsets.UTF_8));
                wordsList.addAll(Arrays.asList(wholeString.split("[ \t\\x0B\f\r\n]+")));
            } else {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    wordsList.addAll(Arrays.asList(line.split("[ \t\\x0B\f\r\n]+")));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsList;
    }


    private String filterWord(String word) {
        if (filterConfig.getQ().equals("")) {
            String acceptable = runLengthFilter(word);
            if (acceptable != null) {
                return acceptable;
            }
        } else {
            String acceptable = runQueryFilter(word);
            if (acceptable != null) {
                return acceptable;
            }
        }
        return null;
    }

    private String runLengthFilter(String word) {
        if (filterConfig.getLength() != null) {
            return (word.length() > filterConfig.getLength()) ? null : word;
        }
        return word;
    }


    private String runQueryFilter(String word) {
        if (filterConfig.getQ() != null && !filterConfig.getQ().equals("")) {
            Pattern pattern = Pattern.compile(filterConfig.getQ());
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()) {
                return runLengthFilter(word);
            } else
                return null;
        } else
            return null;
    }

}
