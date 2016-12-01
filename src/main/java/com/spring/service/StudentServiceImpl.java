package com.spring.service;

import com.spring.controller.APIController;
import com.spring.model.Message;
import com.spring.model.Student;
import com.spring.util.FileUtil;
import com.spring.util.JsonUtil;
import com.spring.util.StudentUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sam on 28/11/2016.
 */
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private JsonUtil jsonUtil = new JsonUtil();
    private FileUtil fileUtil = new FileUtil();
    private JdbcTemplate jdbcTemplate;

    public StudentServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message addStudent(Student student) {
        logger.debug("Start addStudent");
        JSONObject jsonObject;
        JSONArray jsonArray;
        Message message = new Message("Failed", false);
        StudentUtil studentUtil = new StudentUtil();

        student.setId(studentUtil.incrementId());

        jsonObject =  jsonUtil.addToJSONObject(student);
        jsonArray = jsonUtil.getFileJSONArray();

        jsonArray.add(jsonObject);

        if (fileUtil.writeToFile(jsonArray)) {
            message.setMessage("Success");
            message.setStatus(true);
        }

        return message;
    }

    @Override
    public Message updateStudent(Student student) {
        logger.debug("Start updateStudent");
        JSONObject jsonObject;
        JSONArray jsonArray;
        Message message = new Message("Failed", false);

        if (student.getId() > 0) {
            jsonArray = jsonUtil.getFileJSONArray();

            for (Object aJsonArray : jsonArray) {
                jsonObject = (JSONObject) aJsonArray;

                if (jsonObject.get("id").equals(student.getId())) {
                    jsonArray.remove(jsonObject);
                    JSONObject newJsonObject = jsonUtil.addToJSONObject(student);
                    jsonArray.add(newJsonObject);

                    if (fileUtil.writeToFile(jsonArray)) {
                        message.setMessage("Success");
                        message.setStatus(true);
                    }
                }
            }
        }

        return message;
    }

    @Override
    public Message deleteStudentById(long id) {
        logger.debug("Start deleteStudent()");
        JSONObject jsonObject;
        JSONArray jsonArray;
        Message message = new Message("Failed", false);

        if (id > 0) {
            jsonArray = jsonUtil.getFileJSONArray();

            Iterator iterator = jsonArray.iterator();

            while (iterator.hasNext()) {
                jsonObject = (JSONObject) iterator.next();

                if (jsonObject.get("id").equals(id)) {
                    iterator.remove();

                    if (fileUtil.writeToFile(jsonArray)) {
                        message.setMessage("Success");
                        message.setStatus(true);
                    }
                }
            }
        }

        return message;
    }

    @Override
    public Student getStudentById(long id) {
        logger.debug("Start getStudentById");
        Student student = new Student();
        JSONObject jsonObject;

        JSONArray jsonArray = jsonUtil.getFileJSONArray();

        for (Object aJsonArray : jsonArray) {
            jsonObject = (JSONObject) aJsonArray;
            if (jsonObject.get("id").equals(id)) {
                student.setId((Long) jsonObject.get("id"));
                student.setGivenName((String) jsonObject.get("givenName"));
                student.setMiddleName((String) jsonObject.get("middleName"));
                student.setLastName((String) jsonObject.get("lastName"));
                student.setAge((Long) jsonObject.get("age"));
                student.setAddress((String) jsonObject.get("address"));

                break;
            }
        }

        return student;
    }

    @Override
    public List<Student> getStudentListByName(String searchValue) {
        logger.debug("Start getStudentListByName()");
        List<Student> studentList = new ArrayList<Student>();
        JSONObject jsonObject;

        JSONArray jsonArray = jsonUtil.getFileJSONArray();

        for (Object aJsonArray : jsonArray) {
            jsonObject = (JSONObject) aJsonArray;

            if (jsonObject.containsValue(searchValue.toLowerCase())) {
                Student student = new Student();

                student.setId((Long) jsonObject.get("id"));
                student.setGivenName((String) jsonObject.get("givenName"));
                student.setMiddleName((String) jsonObject.get("middleName"));
                student.setLastName((String) jsonObject.get("lastName"));
                student.setAge((Long) jsonObject.get("age"));
                student.setAddress((String) jsonObject.get("address"));

                studentList.add(student);
            }
        }

        return studentList;
    }

    @Override
    public List<Student> getStudentList() {
        logger.debug("Start getStudentList");
        List<Student> studentList = new ArrayList<Student>();
        JSONObject jsonObject;

        JSONArray jsonArray = jsonUtil.getFileJSONArray();

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
