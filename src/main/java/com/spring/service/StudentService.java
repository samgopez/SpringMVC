package com.spring.service;

import com.spring.model.Message;
import com.spring.model.Student;

import java.util.List;

/**
 * Created by Sam on 28/11/2016.
 */
public interface StudentService {

    Message addStudent(Student student);
    Message updateStudent(Student student);
    Message deleteStudentById(long id);
    Student getStudentById(long id);
    List<Student> getStudentList();
}
