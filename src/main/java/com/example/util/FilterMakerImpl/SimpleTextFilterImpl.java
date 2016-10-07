package com.example.util.FilterMakerImpl;

import com.example.repository.dao.MetadataDAO;
import com.example.repository.dao.impl.MetadataDAOImpl;
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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cavayman on 06.10.2016.
 */
public class SimpleTextFilterImpl implements FilterMakerInterface {

    private FilterConfig filterConfig;
    private MetadataService metadataService;

    public SimpleTextFilterImpl() {
        metadataService=new MetadataService();
    }

    public SimpleTextFilterImpl(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public synchronized List<String> filterText() throws IOException {
        List<String> contents = new ArrayList<String>();
        List<String> wordsList = readFile(filterConfig.getFileName());
        for (String word : wordsList) {
            String acceptable = filterWord(word);
            if (acceptable != null) {
                contents.add(acceptable);
            }
        }
        return contents;
    }

    @Override
    public void setFilterConfig(FilterConfig filterConfig) {
    this.filterConfig=filterConfig;
    }

    @Override
    public JSONObject getMetadataJson() {
        Metadata metadata=metadataService.getMetadataByName(Paths.get(filterConfig.getFileName()).getFileName().toString());
     JSONObject metaJson=new JSONObject();
        metaJson.put("id",metadata.getMetadata_id());
        metaJson.put("fileName",metadata.getFileName());
        metaJson.put("fileSize",metadata.getFileSize());
        metaJson.put("fileCreationDate",metadata.getFileCreationDate().toString());
        metaJson.put("fileLastModifiedDate",metadata.getFileLastModifiedDate().toString());
        return metaJson;
    }


    public  List<String> readFile(String fileName) {
        List<String> wordsList=new ArrayList<>();
        try (FileReader fileReader = new FileReader(filterConfig.getFileName());
             BufferedReader reader = new BufferedReader(fileReader);) {
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


    public String filterWord(String word) {
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

    public String runLengthFilter(String word) {
        if (filterConfig.getLength() != null) {
            return (word.length() > filterConfig.getLength()) ? null : word;
        }
        return word;
    }


    public String runQueryFilter(String word) {
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
