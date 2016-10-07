package com.example.web;

import com.example.repository.model.Metadata;
import com.example.util.FilterConfig;
import com.example.util.FilterMakerImpl.SimpleTextFilterImpl;
import com.example.util.FilterMakerInterface;
import com.example.util.JsonMaker;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cavayman on 04.10.2016.
 */
@WebServlet(urlPatterns = "/searchWithParams")
@Resource
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");
        Integer length=req.getParameter("length").equals("")?null:Integer.parseInt(req.getParameter("length"));
        Integer limit=req.getParameter("limit").equals("")?10000:Integer.parseInt(req.getParameter("limit"));
        boolean includeMetaData =Boolean.parseBoolean(req.getParameter("includeMetaData"));
        String fileName = req.getParameter("fileName");

        String appPath = req.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + UploadServlet.SAVE_DIR;

        JsonMaker jsonMaker = new JsonMaker();

        FilterConfig filterConfig = new FilterConfig(savePath + File.separator + fileName);
        filterConfig.setQ(q);
        filterConfig.setLength(length);
        filterConfig.setIncludeMetaData(includeMetaData);
        filterConfig.setLimit(limit);
        FilterMakerInterface filterMakerInterface = new SimpleTextFilterImpl();

        jsonMaker.setFilterMaker(filterMakerInterface);
        jsonMaker.setFilterConfig(filterConfig);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(jsonMaker.make());
        out.flush();

    }
}
