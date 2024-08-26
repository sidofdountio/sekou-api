package com.sidof.repo;

import com.sidof.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author       : sidof  <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a>  <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
@Repository
public interface OptionRepo extends JpaRepository<Option, Long> {
    @Query("SELECT o FROM Option o WHERE o.name = ?1")
    Optional<Option>findByName(String name);
    @Query("" +
            "SELECT CASE WHEN COUNT(o) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Option o WHERE o.name = ?1 AND o.fullName= ?2")
    Boolean selectExistingOptionByNameAndFullName(String name,String fullName);

}
