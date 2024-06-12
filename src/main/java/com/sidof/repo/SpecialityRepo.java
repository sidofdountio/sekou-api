package com.sidof.repo;

import com.sidof.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author       : sidof  <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a>  <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
public interface SpecialityRepo extends JpaRepository<Speciality,Long> {
    Optional<Speciality> findByName(String name);
}
