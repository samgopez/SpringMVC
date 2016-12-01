package com.spring.controller;

import com.spring.model.Message;
import com.spring.model.Student;
import com.spring.service.StudentService;
import com.spring.util.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Sam on 01/12/2016.
 */

@Controller
public class JSPController {

    private static final Logger logger = LoggerFactory.getLogger(JSPController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/viewStudentList")
    public ModelAndView viewStudentList(ModelAndView model) {
        List<Student> studentList = studentService.getStudentList();
        model.addObject("studentList", studentList);
        model.setViewName("viewStudentList");

        return model;
    }

    @RequestMapping(value = "/addStudent")
    public ModelAndView showAddStudentForm(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Student student = new Student();
        model.addObject("command", student);
        model.addObject("message", request.getParameter("message"));
        model.setViewName("addStudentForm");

        return model;
    }

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Student student) {
        ModelAndView model = new ModelAndView();
        Validation validation = new Validation();
        Message message = new Message();

        if (validation.isValid(student)) {
            message = studentService.addStudent(student);
            model.setViewName("redirect:/addStudent?message=" + message.getMessage());

            return model;
        } else {
            message.setMessage("Empty/Null input/s");
            message.setStatus(false);

            model.setViewName("redirect:/addStudent?message=" + message.getMessage());

            return model;
        }
    }

    @RequestMapping(value = "/editStudent/{id}")
    public ModelAndView editStudent(@PathVariable long id, HttpServletRequest request) {
        Student student = studentService.getStudentById(id);
        ModelAndView model = new ModelAndView();
        model.addObject("command", student);
        model.addObject("message", request.getParameter("message"));
        model.setViewName("editStudentForm");

        return model;
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public ModelAndView updateStudent(@ModelAttribute Student student) {
        ModelAndView model = new ModelAndView();
        Validation validation = new Validation();
        Message message = new Message();
        if (validation.isValid(student)) {
            message = studentService.updateStudent(student);
            model.setViewName("redirect:/editStudent/" + student.getId() + "?message=" + message.getMessage());

            return model;
        } else {
            message.setMessage("Empty/Null input/s");
            message.setStatus(false);

            model.setViewName("redirect:/addStudent/" + student.getId() + "?message=" + message.getMessage());

            return model;
        }
    }

    @RequestMapping(value = "/deleteStudent/{id}")
    public ModelAndView deleteStudent(@PathVariable long id) {
        Message message = studentService.deleteStudentById(id);

        return new ModelAndView("redirect:/viewStudentList");
    }

    @RequestMapping(value = "/searchStudent")
    public ModelAndView showSearchStudent() {
        return new ModelAndView("searchStudent");
    }

    @RequestMapping(value = "/searchStudentByName", method = RequestMethod.POST)
    public ModelAndView searchStudentByName(@RequestParam String searchValue) {
        List<Student> studentList = studentService.getStudentListByName(searchValue);
        ModelAndView model = new ModelAndView();

        model.addObject("studentList", studentList);
        model.setViewName("searchStudent");

        return model;
    }
}
