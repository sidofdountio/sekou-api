package com.sidof.service;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.StudentAssessment;
import com.sidof.model.enumeration.Appreciation;
import com.sidof.model.enumeration.AssessmentType;
import com.sidof.repo.StudentAssessmentRepo;
import com.sidof.service.inplementation.StudentAssessmentServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

import static com.sidof.model.enumeration.Appreciation.*;
import static com.sidof.utils.FormatNumber.validNumber;

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
public class StudentAssessmentService implements StudentAssessmentServiceImpl {
    private final StudentAssessmentRepo repo;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    @Override
    public StudentAssessment save(StudentAssessment studentAssessment) throws BadRequestException {
        int studentAssessmentScore = studentAssessment.getScore();
        if (!validNumber(studentAssessmentScore)) {
            log.error("Cannot save this student assessment. Provide valid score.");
            throw new BadRequestException("Cannot save this student assessment. Provide valid score.");
        }

        if(studentAssessmentScore <= 4){
            studentAssessment.setAppreciation(BELOWAVERA);

        }else if(studentAssessmentScore == 5 || studentAssessmentScore <= 9){
            studentAssessment.setAppreciation(WEAK);
        }
        else if( studentAssessmentScore == 10 ||studentAssessmentScore <= 13){
            studentAssessment.setAppreciation(FAIRLYGOOD);
        }
       else if( studentAssessmentScore == 14 ||studentAssessmentScore <= 16){
            studentAssessment.setAppreciation(GOOD);
        }
        else if( studentAssessmentScore == 17 ||studentAssessmentScore <= 20){
            studentAssessment.setAppreciation(VERYGOOD);
        }
        log.info("saving new student assessment {}", studentAssessment);
        return repo.save(studentAssessment);
    }

    @Override
    public StudentAssessment getStudentAssessment(Long studentAssessmentId) throws BadRequestException {
        return repo.findById(studentAssessmentId).orElseThrow(
                () -> new BadRequestException("student assessment id  " + studentAssessmentId + " not found")
        );
    }

    @Override
    public StudentAssessment edit(StudentAssessment studentAssessment) throws BadRequestException {
        boolean existsById = repo.existsById(studentAssessment.getId());
        if (!existsById) {
            log.error("Cannot update student assessment. student assessment not exist.");
            throw new BadRequestException("Cannot update student assessment. student assessment not exist.");
        }
        log.info("Update student assessment");
        return repo.save(studentAssessment);
    }

    @Override
    public List<StudentAssessment> getStudentAssessmentList() {
        log.info("Fetching student assessment");
        return repo.findAll();
    }

    @Override
    public List<StudentAssessment> findByOptionAndLevelAndYearAndAssessmentType(Option option, Level level, Year year, AssessmentType assessmentType) {
        log.info("fetching student assessment by option {}, level {},year {} and assessmentType {}", option, level, year, assessmentType);
        return repo.findByOptionAndLevelAndYearAndAssessmentType(option, level, year, assessmentType);
    }

    @Override
    public List<StudentAssessment> findByOptionAndLevelAndYear(Option option, Level level, Year year) {
        log.info("fetching student assessment by option {}, level {},year {}", option, level, year);
        return repo.findByOptionAndLevelAndYear(option, level, year);

    }
}
