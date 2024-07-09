package com.sidof.service.inplementation;

import com.sidof.model.Assessment;
import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.StudentAssessment;
import com.sidof.model.enumeration.AssessmentType;
import org.apache.coyote.BadRequestException;

import java.time.Year;
import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 06/07/2024  <br>
 * Version    : v1.0.0
 * 76823771
 */
public interface AssessmentServiceImpl {
    Assessment save(Assessment assessment) throws BadRequestException;
    Assessment edit(Assessment assessment) throws BadRequestException;
    List<Assessment> getAssessmentList();
    Assessment getAssessment(Long assessmentId) throws BadRequestException;
    List<Assessment>findByOptionAndLevelAndYear(Option option, Level level, Year year);
    List<Assessment> findByOptionAndLevelAndYearAndAssessmentType(Option option, Level level, Year year, AssessmentType assessmentType);

}
