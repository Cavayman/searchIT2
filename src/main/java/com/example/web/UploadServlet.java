package com.example.web;


import com.example.service.FileService;

import com.example.service.MetadataService;
import com.example.util.FilterConfig;
import com.example.util.FilterMakerImpl.SimpleTextFilterImpl;
import com.example.util.FilterMakerInterface;
import com.example.util.JsonMaker;
import org.json.simple.JSONObject;

import javax.annotation.Resource;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by cavayman on 29.09.2016.
 */
@WebServlet(urlPatterns = "/uploadFile")
@MultipartConfig (maxFileSize=1024*1024*10,      // 10MB
        maxRequestSize=1024*1024*50)
@Resource
public class UploadServlet extends HttpServlet {

    public static final String SAVE_DIR = "uploadFiles";

    private FileService fileService = new FileService();
    private MetadataService metadataService=new MetadataService();

    /**
     * Getting file throu request and saving it in uploads directory
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String fileName = extractFileName(filePart);
        File savedFile=fileService.saveTextFile(filePart,savePath + File.separator + fileName);
        metadataService.saveMetadata(savedFile);

        JsonMaker jsonMaker=new JsonMaker();
        FilterConfig filterConfig=new FilterConfig(savePath + File.separator + fileName);
        FilterMakerInterface filterMakerInterface=new SimpleTextFilterImpl();

        jsonMaker.setFilterMaker(filterMakerInterface);
        jsonMaker.setFilterConfig(filterConfig);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonMaker.make());
        out.flush();
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
