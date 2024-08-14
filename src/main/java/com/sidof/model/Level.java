package com.sidof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

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

    public Level(Long id, String name, List<CourseOffering> courseOfferingList, List<CourseEnrollment> courseEnrollmentList, List<Student> student, List<Assessment> assessments, List<StudentAssessment> studentAssessments, List<Register> registers, List<SchoolFee> schoolFees, List<StudentSchoolFee> studentSchoolFees) {
        this.id = id;
        this.name = name;
        this.courseOfferingList = courseOfferingList;
        this.courseEnrollmentList = courseEnrollmentList;
        this.student = student;
        this.assessments = assessments;
        this.studentAssessments = studentAssessments;
        this.registers = registers;
        this.schoolFees = schoolFees;
        this.studentSchoolFees = studentSchoolFees;
    }

    public Level(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Level() {
    }
}
