package com.volodev.laba3oop.Servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Controller {
    private static String pathStr = "C:\\Users\\elena\\Desktop\\Переезд\\Laba3oop\\src\\main\\webapp\\newData.dat";
    private static Path path = Paths.get(pathStr);

    public static void writeNamesToFile(Names names) throws IOException {
        String seriesString = "";
        seriesString += names.getTitle() + ";";
        seriesString += names.getGenre() + ";";
        seriesString += names.getTime() + ";";
        seriesString += names.getAmount() + ";";
        seriesString += names.getRate() + "\n";
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.write(path, seriesString.getBytes(), StandardOpenOption.APPEND);
    }

    public static ArrayList<Names> readNamesFromFile() throws IOException {
        if (Files.exists(path)) {
            BufferedReader reader = new BufferedReader(new FileReader(pathStr));
            String data = reader.readLine();
            ArrayList<Names> name = new ArrayList<>();
            while (data != null) {
                String[] parameters = data.split(";");
                Names tempSeries = new Names(parameters[0], parameters[1], parameters[2], parameters[3], Integer.parseInt(parameters[4]));
                name.add(tempSeries);
                data = reader.readLine();
            }
            reader.close();
            return name;
        }
        return null;
    }
}