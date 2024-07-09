package com.sidof.service;

import com.sidof.model.CourseOffering;
import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.repo.CourseOfferingRepo;
import com.sidof.service.inplementation.CourseOfferingServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/07/2024  <br>
 * Version    : v1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CourseOfferingService implements CourseOfferingServiceImpl {
    private final CourseOfferingRepo repo;

    @Override
    public List<CourseOffering> getCourseOfferings() {
        log.info("Fetching course offering");
        return repo.findAll();
    }

    @Override
    public List<CourseOffering> findByOptionAndLevelAndYear(Option option, Level level, Year year) {
        log.info("Fetching course offering by option {}, level {} and year {}", option, level, year);
        return repo.findByOptionAndLevelAndYear(option, level, year);
    }

    @Override
    public CourseOffering save(CourseOffering courseOffering) throws BadRequestException {
        if (courseOffering.getStarTime() == null) {
            log.error("Cannot save. Please provide start time");
            throw new NullPointerException("Cannot save. Please provide start time");
        }
        if (courseOffering.getEndTime() == null) {
            log.error("Cannot save. Please provide end time");
            throw new BadRequestException("Cannot save. Please provide end time");
        }
        if (courseOffering.getStarTime().equals(courseOffering.getEndTime())) {
            log.error("Cannot save. Please provide different time");
            throw new NullPointerException("Cannot save. Please provide different time");
        }
        courseOffering.setYear(Year.now());
        log.info("saving new course offering {}", courseOffering);
        return repo.save(courseOffering);
    }

    @Override
    public CourseOffering edite(CourseOffering courseOffering) throws BadRequestException {
        boolean existsById = repo.existsById(courseOffering.getId());
        if (!existsById) {
            throw new BadRequestException("Cannot edite");
        }
        return repo.save(courseOffering);
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public CourseOffering getCourseOffering(Long courseOfferingId) throws BadRequestException {
        log.info("Fetching courseOffering by id : {}", courseOfferingId);
        return repo.findById(courseOfferingId).orElseThrow(
                () -> new BadRequestException("courseOffering id not found")
        );
    }
}
