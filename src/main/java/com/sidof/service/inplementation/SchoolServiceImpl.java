package com.sidof.service.inplementation;

import com.sidof.model.School;
import org.apache.coyote.BadRequestException;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 23/08/2024  <br>
 * Version    : v1.0.0
 */
public interface SchoolServiceImpl {
    School save(School schoolToSave);
    School edite(School schoolToSave) throws BadRequestException;
    School getSchool(Long id);
    School getSchoolByActive(boolean active);
}
