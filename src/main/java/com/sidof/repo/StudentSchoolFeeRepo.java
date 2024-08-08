package com.sidof.repo;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.StudentSchoolFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 06/07/2024  <br>
 * Version    : v1.0.0
 */
public interface StudentSchoolFeeRepo extends JpaRepository<StudentSchoolFee, Long> {
    List<StudentSchoolFee> findByOptionAndLevelAndYear(Option option, Level level, Year year);
}
