package com.example.util;


import org.json.simple.JSONObject;

import java.io.*;

/**
 * Created by cavayman on 05.10.2016.
 */
public class JsonMaker {
    private FilterMakerInterface filterMaker;
    private FilterConfig filterConfig;

    public JsonMaker() {
    }


    public JSONObject make() throws IOException {
        JSONObject jsonObject = new JSONObject();
        if (filterConfig.isIncludeMetaData()) {
            jsonObject.put("metaData", filterMaker.getMetadataJson());
        }
        jsonObject.put("text", filterMaker.filterText());
        return jsonObject;
    }

    public void setFilterMaker(FilterMakerInterface filterMaker) {
        this.filterMaker = filterMaker;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        this.filterMaker.setFilterConfig(filterConfig);
    }
}
