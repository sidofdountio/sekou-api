package com.sidof.repo;

import com.sidof.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Author       : sidof  <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a>  <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
public interface StudentRepo extends JpaRepository<Student,Long> {
    Optional<Student> findByEmail(String email);
    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Student s WHERE s.email = ?1")
    Boolean selectExistsStudentEmail(String email);
}
