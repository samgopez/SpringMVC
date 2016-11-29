package com.spring.util;

import com.spring.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sam on 28/11/2016.
 */
public class Validation {

    private static final Logger logger = LoggerFactory.getLogger(StudentUtil.class);

    public boolean isValid(long id) {
        logger.debug("Start isValid() for id");

        boolean status = id <= 0 ? false : true;

        return status;
    }

    public boolean isValid(Student student) {
        logger.debug("Start isValid() for student");

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
