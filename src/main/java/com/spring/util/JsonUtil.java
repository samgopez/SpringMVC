package com.spring.util;

import com.spring.model.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Sam on 28/11/2016.
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public JSONObject addToJSONObject(Student student) {
        logger.debug("Start addToJSONObject()");
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", student.getId());
        jsonObject.put("givenName", student.getGivenName());
        jsonObject.put("middleName", student.getMiddleName());
        jsonObject.put("lastName", student.getLastName());
        jsonObject.put("age", student.getAge());
        jsonObject.put("address", student.getAddress());

        return jsonObject;
    }

    public JSONArray getFileJSONArray() {
        logger.debug("Start getFileJSONArray()");
        FileUtil fileUtil = new FileUtil();
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        try {
            FileReader fileReader = fileUtil.getFile();
            jsonArray = (JSONArray) jsonParser.parse(fileReader);

            fileReader.close();
        } catch (IOException e) {
            logger.error("Error: ", e);
        } catch (ParseException e) {
            logger.error("Error: ", e);
        }

        return jsonArray;
    }

}
