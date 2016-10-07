package com.example.util;

/**
 * Created by cavayman on 06.10.2016.
 */
public class FilterConfig {
    private Integer limit;
    private String q;
    private Integer length;
    private boolean includeMetaData;
    private String fileName;

    public FilterConfig(String fileName){
        this.fileName = fileName;
        this.limit = 10000;
        this.q = "";
        this.length = null;
        this.includeMetaData = true;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public boolean isIncludeMetaData() {
        return this.includeMetaData;
    }

    public void setIncludeMetaData(boolean includeMetaData) {
        this.includeMetaData = includeMetaData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
