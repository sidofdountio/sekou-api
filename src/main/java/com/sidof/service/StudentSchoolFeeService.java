package com.sidof.service;

import com.sidof.model.*;
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

import static com.sidof.model.enumeration.SchoolPayStatus.PAY;
import static com.sidof.model.enumeration.SchoolPayStatus.PENDING;
import static com.sidof.utils.FormatNumber.validNumber;
import static java.time.LocalDateTime.now;

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
        var studentById = getStudentById(studentSchoolFeeToSave);
        SchoolFee schoolFeeByOptionAndLevel = getSchoolFeeByOptionAndLevel(studentSchoolFeeToSave);
        double firstPay = studentSchoolFeeToSave.getFirstPay();
        double secondPay = studentSchoolFeeToSave.getSecondPay();
        double thirdPay = studentSchoolFeeToSave.getThirdPay();
        double schoolFeeTotal = studentSchoolFeeToSave.getSchoolFeeTotal();
        if (!validNumber(schoolFeeTotal)) {
            log.error("Invalid total Fee {}", schoolFeeTotal);
            throw new IllegalArgumentException("Invalid total fee");
        }
        if (!validNumber(firstPay)) {
            log.error("Invalid number for first pay {}", firstPay);
            throw new IllegalArgumentException("Invalid number for first pay ");
        }

        if (!validNumber(secondPay)) {
            log.error("Invalid number for second pay {}", firstPay);
            throw new IllegalArgumentException("Invalid number for second pay ");
        }

        if (!validNumber(thirdPay)) {
            log.error("Invalid number for third pay {}", thirdPay);
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
            //            Change student pay status.
            studentById.setSchoolPayStatus(PENDING);
            double sumOfSchoolFeePay = firstPay + secondPay + thirdPay;
            studentSchoolFeeToSave.setSchoolFeeTotal(sumOfSchoolFeePay);

        }
        /**
         * Check weather the provide school fee its matcher with normal school fee for that class.
         */
        if (studentSchoolFeeToSave.isPayOneTime()) {
            studentSchoolFeeToSave.setFirstPay(0);
            studentSchoolFeeToSave.setSecondPay(0);
            studentSchoolFeeToSave.setThirdPay(0);
            studentSchoolFeeToSave.setPayMultiTime(false);
            if (schoolFeeTotal != schoolFeeByOptionAndLevel.getTotalFee()) {
                log.error("School fee not match with require {}", schoolFeeByOptionAndLevel.getTotalFee());
                throw new IllegalArgumentException("School fee not match with require");
            }
//            Change student pay status.
            studentById.setSchoolPayStatus(PAY);
        }
//        Save STudent Change.
        studentRepo.save(studentById);
        studentSchoolFeeToSave.setLatestDate(now());
        log.info("saving new schoolFee {}", studentSchoolFeeToSave);
        return studentSchoolFeeRepo.save(studentSchoolFeeToSave);
    }

    private Student getStudentById(StudentSchoolFee studentSchoolFeeToSave) throws BadRequestException {
        var studentById = studentRepo.findById(studentSchoolFeeToSave.getStudent().getId()).orElseThrow(
                () -> new BadRequestException("Student ID not found"));
        return studentById;
    }

    private SchoolFee getSchoolFeeByOptionAndLevel(StudentSchoolFee studentSchoolFeeToSave) {
        SchoolFee schoolFeeByOptionAndLevel = schoolFeeRepo.findSchoolFeeByOptionAndLevel(
                studentSchoolFeeToSave.getOption(),
                studentSchoolFeeToSave.getLevel());
        return schoolFeeByOptionAndLevel;
    }

    @Override
    public StudentSchoolFee edit(StudentSchoolFee studentSchoolFeeToEdit) throws BadRequestException {
        var studentById = getStudentById(studentSchoolFeeToEdit);
        SchoolFee schoolFeeByOptionAndLevel = getSchoolFeeByOptionAndLevel(studentSchoolFeeToEdit);
        double totalFeeSchool = schoolFeeByOptionAndLevel.getTotalFee();
        double firstPay = studentSchoolFeeToEdit.getFirstPay();
        double thirdPay = studentSchoolFeeToEdit.getThirdPay();
        double secondPay = studentSchoolFeeToEdit.getSecondPay();
        double schoolFeeTotal = studentSchoolFeeToEdit.getSchoolFeeTotal();
        boolean existsById = studentSchoolFeeRepo.existsById(studentSchoolFeeToEdit.getId());
        boolean finalStep = false;
        studentSchoolFeeToEdit.setPayMultiTime(true);
        studentSchoolFeeToEdit.setPayOneTime(false);
        if (!existsById) {
            throw new BadRequestException("Student school Fee ID not found");
        }
        if (!validNumber(schoolFeeTotal)) {
            log.error("Invalid total Fee {}", schoolFeeTotal);
            throw new IllegalArgumentException("Invalid total fee");
        }

        if (!validNumber(firstPay)) {
            log.error("Invalid number for first pay {}", firstPay);
            throw new IllegalArgumentException("Invalid number for first pay ");
        }
        if (!validNumber(secondPay)) {
            log.error("Invalid number for second pay {}", firstPay);
            throw new IllegalArgumentException("Invalid number for second pay ");
        }
        if (!validNumber(thirdPay)) {
            log.error("Invalid number for third pay {}", thirdPay);
            throw new IllegalArgumentException("Invalid number for third pay ");
        }

        if (firstPay != 0) {
            if (secondPay == 0) {
                log.error("Second pay for chool fee cannot be 0 since first pay has value.");
                throw new BadRequestException("Second pay for school fee cannot be 0 since first pay has value.");
            }
        } else {
            log.error("First pay for school fee cannot be 0");
            throw new BadRequestException("First pay for school fee cannot be 0");
        }
//        if (finalStep) {
//            if (thirdPay == 0) {
//                log.error("Third pay for chool fee cannot be 0 since second pay has value.");
//                throw new BadRequestException("Third pay for chool fee cannot be 0 since second pay has value.");
//            }
//        }
        double sumOfSchoolFeePay = firstPay + secondPay + thirdPay;
        /**
         * Require school fee schould be equal to the addition of  totalSchoolFee.
         * After that student will have status pay == PAY.
         */
        if (sumOfSchoolFeePay == totalFeeSchool) {
            studentById.setSchoolPayStatus(PAY);
        } else {
            studentById.setSchoolPayStatus(PENDING);
        }
        studentSchoolFeeToEdit.setSchoolFeeTotal(sumOfSchoolFeePay);
        studentSchoolFeeToEdit.setLatestDate(now());
        studentRepo.save(studentById);
        log.info("Updating student school fee {} ", studentSchoolFeeToEdit);
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
