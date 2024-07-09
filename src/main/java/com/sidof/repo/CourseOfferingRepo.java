package com.sidof.repo;

import com.sidof.model.CourseOffering;
import com.sidof.model.Level;
import com.sidof.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.Year;
import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 06/07/2024  <br>
 * Version    : v1.0.0
 */
public interface CourseOfferingRepo extends JpaRepository<CourseOffering, Long> {
    @Query("SELECT c FROM CourseOffering c WHERE c.option = ?1 AND c.level = ?2 AND c.year = ?3 ")
    List<CourseOffering> findByOptionAndLevelAndYear(Option option, Level level, Year year);



}
