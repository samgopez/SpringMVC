package com.spring.util;

import com.spring.model.Student;

/**
 * Created by Sam on 28/11/2016.
 */
public class Validation {

    public boolean isValid(long id) {

        boolean status = id <= 0 ? false : true;

        return status;
    }

    public boolean isValid(Student student) {
        boolean status = true;

        if (student == null) {
            status = false;
        } else if (student.getGivenName().isEmpty() || student.getGivenName() == null) {
            status = false;
        } else if (student.getMiddleName().isEmpty() || student.getMiddleName() == null) {
            status = false;
        } else if (student.getLastName().isEmpty() || student.getLastName() == null) {
            status = false;
        } else if (student.getAddress().isEmpty() || student.getAddress() == null) {
            status = false;
        } else if (student.getAge() == 0) {
            status = false;
        }

        return status;
    }
}
