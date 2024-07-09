package com.sidof.service.inplementation;

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
 */
public interface StudentAssessmentServiceImpl {
    StudentAssessment save(StudentAssessment studentAssessment) throws BadRequestException;
    StudentAssessment getStudentAssessment(Long studentAssessmentId) throws BadRequestException;
    StudentAssessment edit(StudentAssessment studentAssessment) throws BadRequestException;
    List<StudentAssessment> getStudentAssessmentList();
    List<StudentAssessment> findByOptionAndLevelAndYearAndAssessmentType(Option option, Level level, Year year, AssessmentType assessmentType);
    List<StudentAssessment> findByOptionAndLevelAndYear(Option option, Level level, Year year);
}
