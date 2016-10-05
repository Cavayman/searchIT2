package com.example.util;


import com.example.repository.model.Metadata;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cavayman on 05.10.2016.
 */
public class JsonMaker {
    private Integer limit;
    private String q;
    private Integer length;
    private boolean includeMetaData;
    private String fileName;
    private Metadata metadata;
    private String text;
    private JSONObject jsonObject;


    public JsonMaker(String fileName) {
        this.fileName = fileName;
        this.limit = 5;
        this.q = "";
        this.length = null;
        this.includeMetaData = false;
    }

    public JSONObject make() throws IOException {
        jsonObject=new JSONObject();
        if (includeMetaData) {
            jsonObject.put("metaData", metadata);
        }
        jsonObject.put("text", filterText());
        return jsonObject;
    }

    private synchronized List<String> filterText() throws IOException {
        List<String> contents = new ArrayList<String>();
        BufferedReader reader = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            reader = new BufferedReader(fileReader);
            String line = "";

            while ((line = reader.readLine()) != null) {
                String[] wordsList = line.split(" ");
                for (String word : wordsList) {
                    if (q.equals("")) {
                        String acceptable = runLengthFilter(word);
                        if (acceptable != null) {
                            contents.add(acceptable);
                        }
                    } else {
                        String acceptable = runQueryFilter(word);
                        if (acceptable != null) {
                            contents.add(acceptable);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
        return contents;
    }

    private String runLengthFilter(String word) {
        if (length != null) {
            return (word.length() > length) ? null: word;
        }
        return word;
    }

    private String runQueryFilter(String word) {
        if (q != null && !q.equals("")) {
            Pattern pattern = Pattern.compile(q);
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()) {
                return runLengthFilter(word);
            } else
                return null;
        } else
            return null;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setIncludeMetaData(boolean includeMetaData) {
        this.includeMetaData = includeMetaData;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
