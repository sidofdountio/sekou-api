package com.sidof.service;

import com.sidof.model.Course;
import com.sidof.repo.CourseRepo;
import com.sidof.service.inplementation.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService implements CourseServiceImpl {
    private final CourseRepo courseRepo;

    @Override
    public Course save(Course courseToSave) throws BadRequestException {
        Optional<Course> courseTitle = courseRepo.findByTitle(courseToSave.getTitle());
        if(courseTitle.isPresent()){
            throw new BadRequestException("Course with this title "+courseToSave.getTitle()+" exist");
        }
        log.info("Saving new courses {}",courseToSave);
        return courseRepo.save(courseToSave);
    }

    @Override
    public Course update(Course courseToEdit) throws BadRequestException {
        boolean existsById = courseRepo.existsById(courseToEdit.getId());
        if(existsById){
            throw new BadRequestException("Course not exist");
        }
        return courseRepo.save(courseToEdit);
    }

    @Override
    public Course getCourse(Long id) throws BadRequestException {
        if(!courseRepo.existsById(id)){
            log.info("The provide id {} exist",id);
            throw new BadRequestException("The provide id " + id + "exist");
        }
        return courseRepo.findById(id).get();
    }

    @Override
    public List<Course> getCourses() {
        log.info("Fetching courses");
        return courseRepo.findAll();
    }
}
