package com.sidof.service;

import com.sidof.dto.TeacherDto;
import com.sidof.model.Teacher;
import com.sidof.repo.TeacherRepo;
import com.sidof.service.inplementation.TeacherServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/07/2024  <br>
 * Version    : v1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TeacherService implements TeacherServiceImpl {
    private final TeacherRepo repo;

    @Override
    public List<Teacher> getTeachers() {
        return repo.findAll();
    }

    @Override
    public Teacher getTeacher(Long TeacherId) {
        return repo.findById(TeacherId).orElse(null);
    }

    @Override
    public Teacher save(TeacherDto dto) throws BadRequestException {
        var teacher = TeacherDto.convertTeacherDTOToTeacher(dto);
        log.info("saving new teacher");
        return repo.save(teacher);
    }

    @Override
    public Teacher edit(Teacher teacher) throws BadRequestException {
        boolean existsById = repo.existsById(teacher.getId());
        if (!existsById) {
            throw new BadRequestException("Cannot edite");
        }
        return repo.save(teacher);
    }

    @Override
    public Boolean delete(Long teacherId) throws BadRequestException {
        return null;
    }
}
