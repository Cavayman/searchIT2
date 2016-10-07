package com.example.util;

import com.example.repository.model.Metadata;
import com.example.util.FilterConfig;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by cavayman on 06.10.2016.
 */
public interface FilterMakerInterface {

    List<String> filterText() throws IOException;

    void setFilterConfig(FilterConfig filterConfig);

    JSONObject getMetadataJson();
}
