package com.spring.controller;

import com.spring.model.Message;
import com.spring.model.Student;
import com.spring.service.StudentService;
import com.spring.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sam on 28/11/2016.
 */

@RestController
public class APIController {

    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    private StudentService studentService;

    public APIController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/API/addStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Message> addStudent(@ModelAttribute Student student) {
        logger.debug("Start addStudent for /API/addStudent");
        Validation validation = new Validation();
        Message message = new Message();

        if (validation.isValid(student)) {
            message = studentService.addStudent(student);

            return new ResponseEntity<Message>(message, HttpStatus.OK);
        } else {
            message.setMessage("Empty/Null input/s");
            message.setStatus(false);

            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/API/updateStudent", method = RequestMethod.PUT)
    public ResponseEntity<Message> updateStudent(@ModelAttribute Student student) {
        logger.debug("Start updateStudent for /API/updateStudent");
        Validation validation = new Validation();
        Message message = new Message();

        if (validation.isValid(student)) {
        message = studentService.updateStudent(student);

            return new ResponseEntity<Message>(message, HttpStatus.OK);
        } else {
            message.setMessage("Empty/Null input/s");
            message.setStatus(false);

            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/API/viewStudentList", method = RequestMethod.POST)
    public ResponseEntity<List<Student>> viewStudent() {
        List<Student> studentList = studentService.getStudentList();

        return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
    }
}
