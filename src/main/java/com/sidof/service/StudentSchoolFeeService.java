package com.sidof.service;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.SchoolFee;
import com.sidof.model.StudentSchoolFee;
import com.sidof.repo.SchoolFeeRepo;
import com.sidof.repo.StudentRepo;
import com.sidof.repo.StudentSchoolFeeRepo;
import com.sidof.service.inplementation.StudentSchoolFeeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

import static com.sidof.utils.FormatNumber.validNumber;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/08/2024  <br>
 * Version    : v1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StudentSchoolFeeService implements StudentSchoolFeeServiceImpl {
    private final StudentSchoolFeeRepo schoolFeeToSave;
    private final StudentRepo studentRepo;
    private final SchoolFeeRepo schoolFeeRepo;
    private final StudentSchoolFeeRepo studentSchoolFeeRepo;


    /**
     * @param studentSchoolFeeToSave
     * @return
     * @throws BadRequestException There are many if statement in this method.
     *                             These three first statement check weather provide number its valid.
     */
    @Override
    public StudentSchoolFee save(StudentSchoolFee studentSchoolFeeToSave) throws BadRequestException {
        List<SchoolFee> schoolFeeByOptionAndLevel = schoolFeeRepo.findByOptionAndLevel(studentSchoolFeeToSave.getOption(), studentSchoolFeeToSave.getLevel());
        var schoolFee = new SchoolFee();
        for (SchoolFee currentSchoolFee : schoolFeeByOptionAndLevel) {
            schoolFee = currentSchoolFee;
        }
        double firstPay = studentSchoolFeeToSave.getFirstPay();
        double schoolFeeTotal = studentSchoolFeeToSave.getSchoolFeeTotal();
        if (!validNumber(schoolFeeTotal)) {
            log.error("Invalid total Fee {}", schoolFeeTotal);
            throw new IllegalArgumentException("Invalid total fee");
        }

        if (!validNumber(firstPay)) {
            log.error("Invalid number for first pay {}", firstPay);
            throw new IllegalArgumentException("Invalid number for first pay ");
        }
        if (!validNumber(studentSchoolFeeToSave.getSecondPay())) {
            log.error("Invalid number for second pay {}", firstPay);
            throw new IllegalArgumentException("Invalid number for second pay ");
        }
        if (!validNumber(studentSchoolFeeToSave.getThirdPay())) {
            log.error("Invalid number for third pay {}", studentSchoolFeeToSave.getThirdPay());
            throw new IllegalArgumentException("Invalid number for third pay ");
        }
        /**
         * Check if student want to in multiple step.
         * Make sure that the firstPay it not equals to O.
         */
        if (studentSchoolFeeToSave.isPayMultiTime()) {
            studentSchoolFeeToSave.setPayOneTime(false);
            studentSchoolFeeToSave.setSchoolFeeTotal(firstPay);
            if (firstPay == 0) {
                log.error("First pay cannot be equals to 0 since select multi pay");
                throw new IllegalArgumentException("First pay cannot be equals to 0 since select multi pay");
            }
        }

        /**
         * Check weather the provide school fee its matcher with normal school fee for that class.
         */
        if (studentSchoolFeeToSave.isPayOneTime()) {
            studentSchoolFeeToSave.setFirstPay(0);
            studentSchoolFeeToSave.setSecondPay(0);
            studentSchoolFeeToSave.setThirdPay(0);
            studentSchoolFeeToSave.setPayMultiTime(false);
            if (schoolFeeTotal != schoolFee.getTotalFee()) {
                log.error("School fee not match with require {}", schoolFee.getTotalFee());
                throw new IllegalArgumentException("School fee not match with require");
            }
        }
        log.info("saving new schoolFee {}", schoolFeeToSave);
        return studentSchoolFeeRepo.save(studentSchoolFeeToSave);
    }

    @Override
    public StudentSchoolFee edit(StudentSchoolFee studentSchoolFeeToEdit) throws BadRequestException {
        double firstPay = studentSchoolFeeToEdit.getFirstPay();
        boolean existsById = studentSchoolFeeRepo.existsById(studentSchoolFeeToEdit.getId());
        double schoolFeeTotal = studentSchoolFeeToEdit.getSchoolFeeTotal();
        if (!existsById) {
            throw new BadRequestException("ID not found");
        }
        if (!validNumber(schoolFeeTotal)) {
            log.error("Invalid total Fee {}", schoolFeeTotal);
            throw new IllegalArgumentException("Invalid total fee");
        }

        if (!validNumber(firstPay)) {
            log.error("Invalid number for first pay {}", firstPay);
            throw new IllegalArgumentException("Invalid number for first pay ");
        }
        if (!validNumber(studentSchoolFeeToEdit.getSecondPay())) {
            log.error("Invalid number for second pay {}", firstPay);
            throw new IllegalArgumentException("Invalid number for second pay ");
        }
        if (!validNumber(studentSchoolFeeToEdit.getThirdPay())) {
            log.error("Invalid number for third pay {}", studentSchoolFeeToEdit.getThirdPay());
            throw new IllegalArgumentException("Invalid number for third pay ");
        }
        log.info("Updating student school fee {} ",studentSchoolFeeToEdit);
        return studentSchoolFeeRepo.save(studentSchoolFeeToEdit);
    }

    @Override
    public List<StudentSchoolFee> getStudentSchoolFees() {
        log.info("Fecthing school fee list");
        return studentSchoolFeeRepo.findAll();
    }

    @Override
    public StudentSchoolFee getStudentSchoolFee(Long studentSchoolId) throws BadRequestException {
        return studentSchoolFeeRepo.findById(studentSchoolId).orElseThrow(() ->
                new BadRequestException("Cannot find ID")
        );
    }

    @Override
    public List<StudentSchoolFee> findByOptionAndLevelAndYear(Option option, Level level, Year year) {
        log.info("Fecthing student school  fe bay Option {} level {} and year {}", option, level, year);
        return studentSchoolFeeRepo.findByOptionAndLevelAndYear(option, level, year);
    }
}
