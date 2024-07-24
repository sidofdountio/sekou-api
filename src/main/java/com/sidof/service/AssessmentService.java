package com.sidof.service;

import com.sidof.model.Assessment;
import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.enumeration.AssessmentType;
import com.sidof.repo.AssessmentRepo;
import com.sidof.service.inplementation.AssessmentServiceImpl;
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
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AssessmentService implements AssessmentServiceImpl {
    private final AssessmentRepo repo;

    @Override
    public Assessment save(Assessment assessment) throws BadRequestException {
        log.info("saving assessment {}", assessment);
        return repo.save(assessment);
    }

    @Override
    public Assessment edit(Assessment assessment) throws BadRequestException {
        boolean existsById = repo.existsById(assessment.getId());
        if (!existsById) {
            log.error("Cannot update assessment. Assessment not exist.");
            throw new BadRequestException("Cannot update assessment. Assessment not exist.");
        }
        log.info("Update assessment");
        return repo.save(assessment);
    }

    @Override
    public List<Assessment> getAssessmentList() {
        log.info("Fetching assessments");
        return repo.findAll();
    }

    @Override
    public Assessment getAssessment(Long assessmentId) throws BadRequestException {
        log.info("Fetching assessment by id : {}", assessmentId);
        return repo.findById(assessmentId).orElseThrow(
                () -> new BadRequestException("Assessment id not found")
        );
    }

    @Override
    public List<Assessment> findByOptionAndLevelAndYear(Option option, Level level, Year year) {
        log.info("fetching assessments {}, {}, {}", option, level, year);
        return repo.findByOptionAndLevelAndYear(option, level, year);
    }

    @Override
    public List<Assessment> findByOptionAndLevelAndYearAndAssessmentType(Option option, Level level, Year year, AssessmentType assessmentType) {
        log.info("fetching assessments {}, {}, {},{}", option, level, year, assessmentType);
        return repo.findByOptionAndLevelAndYearAndAssessmentType(option, level, year, assessmentType);
    }
}
