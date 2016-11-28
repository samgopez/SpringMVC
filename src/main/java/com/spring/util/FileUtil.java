package com.spring.util;

import org.json.simple.JSONArray;

import java.io.*;

/**
 * Created by Sam on 28/11/2016.
 */
public class FileUtil {

    public boolean writeToFile(JSONArray jsonArray) {
        boolean status = true;
        try {
            File file = new File("D:\\JSON\\students.json");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toJSONString());

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
            status = false;
        }

        return status;
    }

    public FileReader getFile() {
        try {
            return new FileReader("D:\\JSON\\students.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
