package com.sidof.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 19/07/2024  <br>
 * Version    : v1.0.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AssessmentPeriod {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "assessment_period_sequence")
    @SequenceGenerator(name = "assessment_period_sequence", allocationSize = 1, sequenceName = "assessment_period_sequence")
    private Long id;
    @Column(nullable = false,unique = true)
    private LocalDate date;
    @Column(nullable = false,unique = true)
    private LocalDate due;
    private Year year;
}
