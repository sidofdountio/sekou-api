package com.sidof.service.inplementation;

import com.sidof.dto.StudentDto;
import com.sidof.dto.StudentRequest;
import com.sidof.model.Student;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 * Author       : sidof  <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a>  <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
public interface StudentServiceImpl {
    List<Student> getStudents();
    Student save(StudentRequest studentRequest) throws BadRequestException;
    Student update(StudentDto studentDto) throws BadRequestException;
    Student getStudent(Long id) throws BadRequestException;
}
