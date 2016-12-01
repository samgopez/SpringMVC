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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 28/11/2016.
 */
public class StudentUtil {

    private static final Logger logger = LoggerFactory.getLogger(StudentUtil.class);

    public long incrementId() {
        logger.debug("Start incrementId()");
        JSONArray jsonArray = this.getFileJSONArray();
        long lastId = 0;

        if (jsonArray.isEmpty()) {
            lastId += 1;
        }

        for (Object aJsonArray: jsonArray) {
            JSONObject jsonObject = (JSONObject) aJsonArray;
            lastId = (Long) jsonObject.get("id");
            lastId += 1;
        }

        return lastId;
    }

    public JSONObject addToJSONObject(Student student) {
        logger.debug("Start addToJSONObject()");
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", student.getId());
        jsonObject.put("givenName", student.getGivenName().toLowerCase());
        jsonObject.put("middleName", student.getMiddleName().toLowerCase());
        jsonObject.put("lastName", student.getLastName().toLowerCase());
        jsonObject.put("age", student.getAge());
        jsonObject.put("address", student.getAddress().toLowerCase());

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
            //logger.error("Error: ", e);
            return jsonArray;
        }

        return jsonArray;
    }

    public List<Student> addJsonArrayToList(JSONArray jsonArray) {
        List<Student> studentList = new ArrayList<Student>();
        JSONObject jsonObject;

        for (Object aJsonArray : jsonArray) {
            jsonObject = (JSONObject) aJsonArray;
            Student student = new Student();

            student.setId((Long) jsonObject.get("id"));
            student.setGivenName((String) jsonObject.get("givenName"));
            student.setMiddleName((String) jsonObject.get("middleName"));
            student.setLastName((String) jsonObject.get("lastName"));
            student.setAge((Long) jsonObject.get("age"));
            student.setAddress((String) jsonObject.get("address"));

            studentList.add(student);
        }

        return studentList;
    }
}
