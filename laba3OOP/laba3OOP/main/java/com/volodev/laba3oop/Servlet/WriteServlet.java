package com.volodev.laba3oop.Servlet;

import java.io.IOException;
import java.lang.reflect.Type;
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
import com.google.gson.reflect.TypeToken;

@WebServlet("/write")
public class WriteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        sc.getRequestDispatcher("/jsp/write.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathStr = "C:\\Users\\elena\\Desktop\\Переезд\\Laba3oop\\src\\main\\webapp\\newData.dat";
        Path path = Paths.get(pathStr);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Names> SeriesList = new ArrayList<>();
        String jsonArrayString = "";
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        jsonArrayString = Files.readString(path);
        if (jsonArrayString != "") {
            Type listOfNames = new TypeToken<ArrayList<Names>>() {}.getType();
            SeriesList = gson.fromJson(jsonArrayString, listOfNames);
        }
        String data = request.getReader().readLine();
        Names names = gson.fromJson(data, Names.class);
        SeriesList.add(names);
        Controller.writeNamesToFile(names);
    }
}