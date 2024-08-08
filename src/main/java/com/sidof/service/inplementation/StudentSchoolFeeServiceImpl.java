package com.sidof.service.inplementation;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.StudentSchoolFee;
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
public interface StudentSchoolFeeServiceImpl {
    StudentSchoolFee save(StudentSchoolFee studentSchoolFeeToSave) throws BadRequestException;

    StudentSchoolFee edit(StudentSchoolFee studentSchoolFeeToEdit) throws BadRequestException;

    List<StudentSchoolFee> getStudentSchoolFees();

    StudentSchoolFee getStudentSchoolFee(Long studentSchoolId) throws BadRequestException;

    List<StudentSchoolFee> findByOptionAndLevelAndYear(Option option, Level level, Year year);
}
