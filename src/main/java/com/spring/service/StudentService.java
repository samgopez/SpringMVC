package com.spring.service;

import com.spring.model.Message;
import com.spring.model.Student;

import java.util.List;

/**
 * Created by Sam on 28/11/2016.
 */
public interface StudentService {

    Message addStudent(Student student);
    List<Student> getStudentList();
}