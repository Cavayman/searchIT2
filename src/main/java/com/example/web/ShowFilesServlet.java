//package com.example.web;
//
//import com.example.repository.dao.impl.FileDAOImpl;
//import com.example.repository.model.FileModel;
//import com.example.service.FileService;
//import org.json.JSONArray;
//import org.json.simple.JSONObject;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
///**
// * Created by cavayman on 29.09.2016.
// */
//@WebServlet(urlPatterns = "/showAllFiles")
//@Resource
//public class ShowFilesServlet extends HttpServlet {
//
//    FileService fileService = new FileService();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        List<FileModel> fileList = fileService.getAllTextFiles();
//        JSONArray jsonList = new JSONArray();
//        if (fileList!= null) {
//            for (FileModel file : fileList) {
//                jsonList.put(fileService.fromFileToJSON(file));
//            }
//        }
//
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.print(jsonList);
//        out.flush();
//    }
//
//    /**
//     * Searching file by id.Geting only one param ID and gives a text file by it.
//     * @param req
//     * @param resp
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       Integer file_id= Integer.parseInt(req.getParameter("id"));
//        FileModel f=fileService.getFileById(file_id);
//        String text=fileService.getTextFile(f.getFilePath());
//        fileService.currentFile=f;
//        JSONObject obj=fileService.fromFileToJSON(f);
//        obj.put("text",text);
//        resp.setContentType("application/json");
//        PrintWriter out = resp.getWriter();
//        out.print(obj);
//        out.flush();
//    }
//}
