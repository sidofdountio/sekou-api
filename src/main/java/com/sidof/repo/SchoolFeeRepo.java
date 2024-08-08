package com.sidof.repo;

import com.sidof.model.Level;
import com.sidof.model.Option;
import com.sidof.model.SchoolFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/08/2024  <br>
 * Version    : v1.0.0
 */

public interface SchoolFeeRepo extends JpaRepository<SchoolFee, Long> {
    List<SchoolFee> findByOptionAndLevel(Option option, Level level);
//    SchoolFee  findByOptionAndLevel(Option option, Level level);

}
