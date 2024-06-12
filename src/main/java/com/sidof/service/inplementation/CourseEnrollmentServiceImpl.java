package com.sidof.service.inplementation;

import com.sidof.model.CourseEnrollment;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 * Author       : sidof  <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a>  <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
public interface CourseEnrollmentServiceImpl {
    List<CourseEnrollment> getEnrollments();
    CourseEnrollment save(CourseEnrollment courseEnrollmentToSave) throws BadRequestException;
    CourseEnrollment update(CourseEnrollment courseEnrollmentToSave) throws BadRequestException;
    CourseEnrollment getCourseEnrollment(Long id) throws BadRequestException;

}
