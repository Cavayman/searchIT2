package com.example.util;

import com.example.util.FilterMakerImpl.SimpleTextFilterImpl;

import java.io.IOException;

/**
 * Created by cavayman on 05.10.2016.
 */
public class MainTest{


    public static void main(String[]arg) throws IOException {
        FilterConfig filterConfig=new FilterConfig("user_stories.txt");
        filterConfig.setLimit(12);
        filterConfig.setQ("то");
        filterConfig.setLength(3);
        JsonMaker jsonMaker =new JsonMaker();
        jsonMaker.setFilterMaker(new SimpleTextFilterImpl());
        jsonMaker.setFilterConfig(filterConfig);
        System.out.println(jsonMaker.make());

    }
}
//        -limit: integer which represents max number of chars in text that API should return. If
//        parameter is blank or missing return max 10000 chars.
//        - q: string which represents text to search in file, i.e. if it q=java - API should return all
//        strings which equals to ‘java’ or containing it. If q is blank or missing - API should return
//        all text from file
//        - length: integer which represents max string length. API should return string which
//        doesn’t exceed that number or if there is no such strings empty response. If parameter
//        is blank or missing ignore it.
