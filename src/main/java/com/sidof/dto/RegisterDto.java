package com.sidof.dto;

import com.sidof.model.Student;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.Year;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 07/06/2024  <br>
 * Version    : v1.0.0
 */

/**
 *     @ Long id;
 *     @ LocalDate registerDate;
 *     @ Year startDate;
 *     @ Year endDate;
 *     @NumberFormat(pattern = "#,###.00")
 *     @ double feeRegister;
 *     @ double feeTuition;
 *     @ Student student;
 */
@Data
public class RegisterDto {
    private Long id;
    private LocalDate registerDate;
    private Year startDate;
    private Year endDate;
    @NumberFormat(pattern = "#,###.00")
    private double feeRegister;
    //    private double feeTuition;
    private Student student;
}
