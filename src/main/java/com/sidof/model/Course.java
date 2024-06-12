package com.sidof.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
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
public class Course {
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "course_id_sequence")
    @SequenceGenerator(name = "course_id_sequence",allocationSize = 1,sequenceName = "course_id_sequence")
    private Long id;
    @Column(name = "title", unique = true)
    private String title;
    private int credit;
    @OneToMany(mappedBy = "course",fetch = LAZY)
    private List<CourseEnrollment> courseEnrollmentList=new ArrayList<>();

}
