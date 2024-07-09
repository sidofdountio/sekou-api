package com.sidof.service.inplementation;

import com.sidof.dto.TeacherDto;
import com.sidof.model.Teacher;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/07/2024  <br>
 * Version    : v1.0.0
 */
public interface TeacherServiceImpl {
    List<Teacher> getTeachers();
    Teacher getTeacher(Long TeacherId);
    Teacher save(TeacherDto dto) throws BadRequestException;
    Teacher edit(Teacher teacher) throws BadRequestException;
    Boolean delete (Long teacherId) throws BadRequestException;
}
