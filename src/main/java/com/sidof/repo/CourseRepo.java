package com.sidof.repo;

import com.sidof.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author       : sidof  <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a>  <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
public interface CourseRepo extends JpaRepository<Course,Long> {
    Optional<Course> findByTitle(String title);
}
