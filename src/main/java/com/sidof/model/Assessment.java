package com.sidof.model;

import com.sidof.model.enumeration.AssessmentType;

import java.time.LocalDate;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 05/06/2024  <br>
 * Version    : v1.0.0
 */

public class Assessment {
    private Student student;
    private Course course;
    private AssessmentType type;
    private String title;
    private double score;
    private LocalDate date;
}
