package com.volodev.laba3oop.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/read")
public class ReadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher("/jsp/read.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathStr = "C:\\Users\\elena\\Desktop\\Переезд\\Laba3oop\\src\\main\\webapp\\newData.dat";
        Path path = Paths.get(pathStr);
        response.setContentType("application/json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String datArrayString = "1";
        if (Files.exists(path)) {
            datArrayString = Files.readString(path);
        }
        PrintWriter out = response.getWriter(); //
        String jsonArrayString = "";
        if (datArrayString != "") {
            ArrayList<Names> names = Controller.readNamesFromFile();
            jsonArrayString = gson.toJson(names);
        }
        out.print(jsonArrayString);
        out.close();
    }
}