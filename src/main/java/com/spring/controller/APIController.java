package com.spring.controller;

import com.spring.model.Message;
import com.spring.model.Student;
import com.spring.service.StudentService;
import com.spring.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/API/deleteStudent/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> deleteStudentById(@PathVariable long id) {
        logger.debug("Start deleteStudentById for /API/deleteStudent/{id}");
        Validation validation = new Validation();
        Message message = new Message();

        if (validation.isValid(id)) {
            message = studentService.deleteStudentById(id);

            return new ResponseEntity<Message>(message, HttpStatus.OK);
        } else {
            message.setMessage("Invalid id");
            message.setStatus(false);

            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/API/viewStudent/{id}", method = RequestMethod.POST)
    public ResponseEntity viewStudentById(@PathVariable long id) {
        Validation validation = new Validation();
        Student student;

        if (validation.isValid(id)) {
            student = studentService.getStudentById(id);

            if (student.getId() > 0) {
                return new ResponseEntity<Student>(student, HttpStatus.OK);
            } else {
                Message message = new Message("No student found", false);

                return new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
            }
        } else {
            Message message = new Message("Invalid id", false);

            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/API/searchStudentByName/{name}", method = RequestMethod.POST)
    public ResponseEntity<List<Student>> searchStudentByName(@PathVariable String name) {
        List<Student> studentList = studentService.getStudentListByName(name);

        return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/API/viewStudentList", method = RequestMethod.POST)
    public ResponseEntity<List<Student>> viewStudentList() {
        List<Student> studentList = studentService.getStudentList();

        return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
    }
}
