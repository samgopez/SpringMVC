package com.spring.service;

import com.spring.model.Message;
import com.spring.model.Student;
import com.spring.util.FileUtil;
import com.spring.util.JsonUtil;
import com.spring.util.StudentUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 28/11/2016.
 */
public class StudentServiceImpl implements StudentService {

    private JsonUtil jsonUtil = new JsonUtil();
    private FileUtil fileUtil = new FileUtil();
    private JdbcTemplate jdbcTemplate;

    public StudentServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message addStudent(Student student) {
        JSONObject jsonObject;
        JSONArray jsonArray;
        Message message = new Message("failed", false);
        StudentUtil studentUtil = new StudentUtil();

        student.setId(studentUtil.incrementId());

        jsonObject =  jsonUtil.addToJSONObject(student);
        jsonArray = jsonUtil.getFileJSONArray();

        jsonArray.add(jsonObject);

        if (fileUtil.writeToFile(jsonArray)) {
            message.setMessage("success");
            message.setStatus(true);
        }

        return message;
    }

    @Override
    public List<Student> getStudentList() {
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
