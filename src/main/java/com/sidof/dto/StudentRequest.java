package com.sidof.dto;

import com.sidof.model.enumeration.Gender;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */
@Data
@Builder
public class StudentRequest {
    private String firstName;
    private String lastName;
    @Enumerated(STRING)
    private Gender gender;
    private String email;
    private LocalDate dateOfBirth;

}
