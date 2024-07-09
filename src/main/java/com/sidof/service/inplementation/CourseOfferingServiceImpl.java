package com.sidof.service.inplementation;

import com.sidof.model.CourseOffering;
import com.sidof.model.Level;
import com.sidof.model.Option;
import org.apache.coyote.BadRequestException;

import java.time.Year;
import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 06/07/2024  <br>
 * Version    : v1.0.0
 */
public interface CourseOfferingServiceImpl {
    List<CourseOffering>getCourseOfferings();
    List<CourseOffering> findByOptionAndLevelAndYear(Option option, Level level, Year year);
    CourseOffering save(CourseOffering courseOffering) throws  BadRequestException;
    CourseOffering getCourseOffering(Long courseOfferingId) throws  BadRequestException;
    CourseOffering edite(CourseOffering courseOffering) throws  BadRequestException;
    Boolean delete(Long id);
}
