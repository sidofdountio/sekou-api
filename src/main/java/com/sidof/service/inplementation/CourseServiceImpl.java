package com.sidof.service.inplementation;

import com.sidof.model.Course;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 * Author       : sidof  <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a>  <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
public interface CourseServiceImpl {
    Course save(Course courseToSave) throws BadRequestException;
    Course update(Course courseToEdit) throws BadRequestException;
    Course getCourse(Long id) throws BadRequestException;
    List<Course> getCourses();


}
