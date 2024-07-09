package com.sidof.repo;

import com.sidof.model.Assessment;
import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.enumeration.AssessmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 06/07/2024  <br>
 * Version    : v1.0.0
 */
public interface AssessmentRepo extends JpaRepository<Assessment,Long> {
    List<Assessment> findByOptionAndLevelAndYear(Option option, Level level, Year year);
    List<Assessment> findByOptionAndLevelAndYearAndAssessmentType(Option option, Level level, Year year, AssessmentType assessmentType);
}
