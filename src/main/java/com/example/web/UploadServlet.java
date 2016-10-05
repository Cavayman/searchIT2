package com.example.web;



import com.example.repository.model.Metadata;
import com.example.service.FileService;

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
import java.nio.file.Paths;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created by cavayman on 29.09.2016.
 */
@WebServlet(urlPatterns = "/uploadFile")
@MultipartConfig()
@Resource
public class UploadServlet extends HttpServlet {


    private FileService fileService = new FileService();


    /**
     * Getting file throu request and saving it in uploads directory
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        fileService.saveTextFile(filePart);

        JSONObject obj = new JSONObject();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(obj);
        out.flush();
    }
}
