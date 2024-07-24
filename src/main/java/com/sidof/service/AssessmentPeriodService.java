package com.sidof.service;

import com.sidof.model.AssessmentPeriod;
import com.sidof.repo.AssessmentPeriodRepo;
import com.sidof.service.inplementation.AssessmentPeriodServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 22/07/2024  <br>
 * Version    : v1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AssessmentPeriodService implements AssessmentPeriodServiceImpl {
    private final AssessmentPeriodRepo assessmentPeriodRepo;
    @Override
    public AssessmentPeriod save(AssessmentPeriod assessmentPeriod) throws BadRequestException {
        if (assessmentPeriod.getDue().equals(assessmentPeriod.getDate())) {
            log.error("Cannot scheduler this assessment. Provide different date.");
            throw new BadRequestException("Cannot scheduler this assessment. Provide different date.");
        }
        return assessmentPeriodRepo.save(assessmentPeriod);
    }

    @Override
    public List<AssessmentPeriod> getPeriodAssessments() {
        return assessmentPeriodRepo.findAll();
    }

    @Override
    public AssessmentPeriod getPeriod(Long id) throws BadRequestException {
        return assessmentPeriodRepo.findById(id).orElseThrow(
                ()-> new BadRequestException("Id not found")
        );
    }
}
