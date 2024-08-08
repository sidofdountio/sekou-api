package com.sidof.service.inplementation;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.SchoolFee;
import org.apache.coyote.BadRequestException;

import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 06/07/2024  <br>
 * Version    : v1.0.0
 * 76823771
 */
public interface SchoolFeeServiceImpl {
    SchoolFee save(SchoolFee schoolFeeToSave) throws BadRequestException;

    SchoolFee edit(SchoolFee schoolFeeToEdit) throws BadRequestException;

    List<SchoolFee> getSchoolFees();

    SchoolFee getSchoolFee(Long schoolFee) throws BadRequestException;

    List<SchoolFee> findByOptionAndLevelAndYear(Option option, Level level);

}
