package com.sidof.service.inplementation;

import com.sidof.model.AssessmentPeriod;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 22/07/2024  <br>
 * Version    : v1.0.0
 */
public interface AssessmentPeriodServiceImpl {
    AssessmentPeriod save(AssessmentPeriod assessmentPeriod) throws BadRequestException;
    AssessmentPeriod getPeriod(Long id) throws BadRequestException;
    List<AssessmentPeriod>getPeriodAssessments();
}
