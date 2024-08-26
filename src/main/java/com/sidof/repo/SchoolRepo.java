package com.sidof.repo;

import com.sidof.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 23/08/2024  <br>
 * Version    : v1.0.0
 */
public interface SchoolRepo extends JpaRepository<School, Long> {
    School findByActive(boolean active);
}
