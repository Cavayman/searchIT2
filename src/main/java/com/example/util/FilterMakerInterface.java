package com.example.util;


import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by cavayman on 06.10.2016.
 */
public interface FilterMakerInterface {

    List<String> filterText();

    void setFilterConfig(FilterConfig filterConfig);

    JSONObject getMetadataJson();
}
