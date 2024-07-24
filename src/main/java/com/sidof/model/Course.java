package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 05/06/2024  <br>
 * Version    : v1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "course_id_sequence")
    @SequenceGenerator(name = "course_id_sequence", allocationSize = 1, sequenceName = "course_id_sequence")
    private Long id;
    @Column(unique = false)
    private String title;
    private int credit;
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseEnrollment> courseEnrollmentList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Assessment> assessments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseOffering> courseOfferings = new ArrayList<>();

}
