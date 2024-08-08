package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
public class Level {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "level_id_sequence")
    @SequenceGenerator(name = "level_id_sequence", allocationSize = 1, sequenceName = "level_id_sequence")
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private List<CourseOffering> courseOfferingList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private List<CourseEnrollment> courseEnrollmentList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private List<Student> student = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private List<Assessment> assessments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private List<StudentAssessment> studentAssessments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private List<Register> registers = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "level")
    private List<SchoolFee> schoolFees = new ArrayList<>();
    @OneToMany(mappedBy = "level")
    private List<StudentSchoolFee> studentSchoolFees = new ArrayList<>();



}
