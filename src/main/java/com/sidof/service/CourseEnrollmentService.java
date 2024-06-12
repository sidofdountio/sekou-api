package com.sidof.service;

import com.sidof.model.CourseEnrollment;
import com.sidof.repo.CourseEnrollmentRepo;
import com.sidof.repo.CourseRepo;
import com.sidof.repo.LevelRepo;
import com.sidof.repo.OptionRepo;
import com.sidof.service.inplementation.CourseEnrollmentServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourseEnrollmentService implements CourseEnrollmentServiceImpl {
    private final CourseEnrollmentRepo courseEnrollmentRepo;
    private final CourseRepo courseRepo;
    private final LevelRepo levelRepo;
    private final OptionRepo optionRepo;

    @Override
    public List<CourseEnrollment> getEnrollments() {
        log.info("Fetching courseEnrollment");
        return courseEnrollmentRepo.findAll();
    }

    @Override
    public CourseEnrollment save(CourseEnrollment courseEnrollmentToSave) throws BadRequestException {
        Long CourseId = courseEnrollmentToSave.getCourse().getId();
        boolean existsCourseById = courseRepo.existsById(CourseId);
        Long levelId = courseEnrollmentToSave.getLevel().getId();
        boolean existsLevelById = levelRepo.existsById(levelId);
        Long optionId = courseEnrollmentToSave.getOption().getId();
        boolean existsOptionById = optionRepo.existsById(optionId);
        if (!existsCourseById) {
            log.error("Course with id {} does exit", CourseId);
            throw new BadRequestException("Course with id " + CourseId + " does exist");
        }
        if (!existsLevelById) {
            log.error("Level with id {} does exit", levelId);
            throw new BadRequestException("Level with id " + levelId + "does exist");
        }
        if (!existsOptionById) {
            log.error("Option with id {} does exit", optionId);
            throw new BadRequestException("Option with id " + optionId + "does exist");
        }
        log.info("Saving new enrollment {} ", courseEnrollmentToSave);
        return courseEnrollmentRepo.save(courseEnrollmentToSave);
    }

    @Override
    public CourseEnrollment update(CourseEnrollment courseEnrollmentToEdit) throws BadRequestException {
        boolean exists = courseEnrollmentRepo.existsById(courseEnrollmentToEdit.getId());
        if (exists) {
            log.error("CourseEnrollment  id {} does exit", courseEnrollmentToEdit.getId());
            throw new BadRequestException("CourEnrollment  id " + courseEnrollmentToEdit.getId() + "does exit");
        }
        log.info("update enrollment {} ", courseEnrollmentToEdit);
        return courseEnrollmentRepo.save(courseEnrollmentToEdit);
    }

    @Override
    public CourseEnrollment getCourseEnrollment(Long id) throws BadRequestException {
        boolean exists = courseEnrollmentRepo.existsById(id);
        if (exists) {
            log.error("CourseEnrollment  id {} does exit", id);
            throw new BadRequestException("CourEnrollment  id " + id + "does exit");
        }
        log.info("Fetch enrollment id {} ", id);
        return courseEnrollmentRepo.findById(id).get();
    }
}
