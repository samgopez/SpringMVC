package com.spring.util;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by Sam on 28/11/2016.
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public boolean writeToFile(JSONArray jsonArray) {
        logger.debug("Start writeToFile()");
        boolean status = true;
        try {
            File file = new File("C:\\Users\\Sam\\IdeaProjects\\SpringMVC\\json\\students.json");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonArray.toJSONString());

            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            logger.error("Error: ", e);
            status = false;
        }

        return status;
    }

    public FileReader getFile() {
        logger.debug("Start getFile()");
        try {
            File file = new File("C:\\Users\\Sam\\IdeaProjects\\SpringMVC\\json\\students.json");

            return new FileReader(file);
        } catch (FileNotFoundException e) {
            logger.error("Error: ", e);
            return null;
        }
    }
}
